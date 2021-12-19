public class Robot {

    int x, y;
    int curr_x = 0, curr_y = 0;
    Direction direction;

    public Robot(int width, int height) {
        this.x = width;
        this.y = height;
        direction = Direction.East;
    }

    public void step(int num) {
        // Taking one step at a time!
        while (num-- > 0) {

            if (direction == Direction.East) {
                if (curr_x + 1 < x)
                    curr_x++;
                else {
                    curr_y++;
                    direction = Direction.North;
                }
            } else if (direction == Direction.North) {
                if (curr_y + 1 < y)
                    curr_y++;
                else {
                    curr_x--;
                    direction = Direction.West;
                }
            } else if (direction == Direction.West) {
                if (curr_x - 1 >= 0)
                    curr_x--;
                else {
                    curr_y--;
                    direction = Direction.South;
                }
            } else if (direction == Direction.South) {
                if (curr_y - 1 >= 0)
                    curr_y--;
                else {
                    curr_x++;
                    direction = Direction.East;
                }
            }
        }
    }

    public int[] getPos() {
        return new int[]{curr_x, curr_y};
    }

    public String getDir() {
        return direction.toString();
    }
}


enum Direction {
    North, South, East, West;
}