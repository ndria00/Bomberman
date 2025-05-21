package application.model;

import application.Settings;

import java.util.*;

public class World {
    public enum BlockType{EMPTY, PLAYER, WALL, BOMB, BOX, EXPLOSION}
    private BlockType[][] grid;
    private Player player;
    private int size;
    private Map<Position, Bomb> bombs;
    private Set<Position> toRemoveBombs;
    private List<Explosion> explosions;

    public World(){
        size = Settings.WORLD_SIZE;
        grid = new BlockType[size][size];
        player = new Player(new Position(0,0));
        bombs = new HashMap<>();
        toRemoveBombs = new HashSet<>();
        createMap();
        explosions = new ArrayList<>();
    }

    public void createMap(){
        for(int i = 0; i < size; ++i){
            for(int j = 0; j < size; ++j){
                if(i != 0 && j != 0 && i != size-1 && j != size-1){
                    if(i % 2 == 0 && j % 2 != 0)
                        grid[i][j] = BlockType.WALL;
                    else if( i % 2 != 0 && j % 2 != 0)
                        grid[i][j] = BlockType.BOX;
                    else
                        grid[i][j] = BlockType.EMPTY;
                }
                else grid[i][j] = BlockType.EMPTY;
            }
        }
        grid[0][0] = BlockType.PLAYER;
    }

    public boolean isPositionValid(Position position){
        return !(position.x() < 0 || position.x() >= size || position.y() < 0 || position.y() >= size);
    }

    public Player getPlayer() {
        return player;
    }

    public void update(){
        movePlayer();
        explodeBombs();
        tickExplosions();
    }
    public void explodeBombs(){
        boolean exploded;
        do{
            exploded = false;
            for(Bomb b : bombs.values()){
                if(b.isExploded() && !toRemoveBombs.contains(b.getPosition())){
                    bombExploded(b);
                    toRemoveBombs.add(b.getPosition());
                    exploded = true;
                }
            }
        }while(exploded);

        for(Position p : toRemoveBombs){
            bombs.remove(p);
            player.addBomb();
        }
        toRemoveBombs.clear();
    }
    public void movePlayer(){
        if(player.isMoving()) {
            Position posMove = player.simulateMove();
            if (isPositionValid(posMove) && (grid[posMove.x()][posMove.y()] == BlockType.EMPTY || grid[posMove.x()][posMove.y()] == BlockType.BOMB)) {
                grid[player.getPosition().x()][player.getPosition().y()] = BlockType.EMPTY;
                player.move(posMove);
                grid[posMove.x()][posMove.y()] = BlockType.PLAYER;
            }
        }
    }
    public void tickExplosions(){
        List<Explosion> toRemoveExplosions = new ArrayList<>();
        for(Explosion explosion : explosions){
            explosion.tick();
            if(explosion.getTimeLeft() <= 0)
                toRemoveExplosions.add(explosion);
        }
        for(Explosion explosion : toRemoveExplosions)
            explosions.remove(explosion);
    }
    public void updatePlayerDirection(int direction){
        player.setDirection(direction);
    }

    public void createBomb(){
        if(player.getBombsLeft() > 0 && bombs.get(player.getPosition()) == null){
            Position pos = player.getPosition();
            if (grid[pos.x()][pos.y()] == BlockType.PLAYER && bombs.get(player.getPosition()) == null && player.getBombsLeft() > 0) {
                Bomb bomb = new Bomb(new Position(pos.x(), pos.y()));
                bombs.put(bomb.getPosition(), bomb);
                Thread t = new Thread(bomb);
                t.start();
            }
            player.useBomb();
        }
    }

    public void bombExploded(Bomb b){
        Position pos = b.getPosition();
        explosions.add(new Explosion(pos, Settings.EXPLOSION_EFFECT_DURATION));
        int start = Math.max(pos.x() - Settings.BOMB_RADIUS, 0);
        int end = Math.min(pos.x() + Settings.BOMB_RADIUS, Settings.WORLD_SIZE-1);
        for(int i = pos.x(); i <= end; ++i){
            Position cell = new Position(i, pos.y());
            if(!cell.equals(b.getPosition()) && stopAfterCell(cell))
                break;
        }

        for(int i = pos.x(); i >= start; --i){
            Position cell = new Position(i, pos.y());
            if(!cell.equals(b.getPosition()) && stopAfterCell(cell))
                break;
        }

        start = Math.max(pos.y() - Settings.BOMB_RADIUS, 0);
        end = Math.min(pos.y() + Settings.BOMB_RADIUS, Settings.WORLD_SIZE-1);
        for(int i = pos.y(); i <= end; ++i){
            Position cell = new Position(pos.x(), i);
            if(!cell.equals(b.getPosition()) && stopAfterCell(cell))
                break;
        }
        for(int i = pos.y(); i >= start; --i){
            Position cell = new Position(pos.x(), i);
            if(!cell.equals(b.getPosition()) && stopAfterCell(cell))
                break;
        }
    }

    private boolean stopAfterCell(Position pos){
        if(getTypeAt(pos) != BlockType.WALL) explosions.add(new Explosion(pos, Settings.EXPLOSION_EFFECT_DURATION));
        if(bombs.get(pos) != null && !toRemoveBombs.contains(pos)){
            bombs.get(pos).explode();
            return true;
        }
        boolean boxHit = grid[pos.x()][pos.y()] == BlockType.BOX;
        if(boxHit)
            grid[pos.x()][pos.y()] = BlockType.EMPTY;
        return boxHit || grid[pos.x()][pos.y()] == BlockType.WALL;
    }

    public int getSize(){
        return size;
    }

    public Map<Position, Bomb> getBombs(){
        return bombs;
    }

    public List<Explosion> getExplosions(){
        return explosions;
    }

    public BlockType getTypeAt(Position p){
        return grid[p.x()][p.y()];
    }
}

