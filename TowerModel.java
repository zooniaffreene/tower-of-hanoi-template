public class TowerModel {

    private IntegerStack[] towers;
    private int towerHeight;
    private int moveCounter;

    public TowerModel(int height) {
        towerHeight = height;
        towers = new IntegerStack[3];
        for (int i = 0; i < 3; i++) {
            towers[i] = new IntegerStack(height);
        }
        for (int i = height; i > 0; i--) {
            towers[0].push(i);
        }
    }

    public int height() {
        return towerHeight;
    }

    public void move(int source, int destination) {
        if (!towers[source].isEmpty() && (towers[destination].isEmpty() || towers[source].peek() < towers[destination].peek())) {
            int disk = towers[source].pop();
            towers[destination].push(disk);
            moveCounter++;
            System.out.println("Move " + disk + " from " + source + " to " + destination);
        } else {
            System.out.println("Invalid move from " + source + " to " + destination);
        }
    }

    public IntegerStack[] getTowers() {
        return towers;
    }
}

public class TowerSolver {
    private TowerModel model;

    public TowerSolver() {}

    public void solve(TowerModel model) {
        this.model = model;
        moveDisks(model.height(), 0, 2, 1);
    }

    private void moveDisks(int n, int from, int to, int extra) {
        if (n == 1) {
            model.move(from, to);
        } else {
            moveDisks(n - 1, from, extra, to);
            model.move(from, to);
            moveDisks(n - 1, extra, to, from);
        }
    }
}

