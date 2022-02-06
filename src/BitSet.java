import java.util.HashSet;

class Bitset {

    HashSet<Integer> ones = new HashSet<>();
    HashSet<Integer> zeroes = new HashSet<>();
    int size = 0;

    public Bitset(int size) {
        this.size = size;
        for (int i = 0; i < size; i++)
            zeroes.add(i);
    }

    public void fix(int idx) {
        ones.add(idx);
        zeroes.remove(idx);
    }

    public void unfix(int idx) {
        zeroes.add(idx);
        ones.remove(idx);
    }

    public void flip() {
        HashSet<Integer> temp = ones;
        ones = zeroes;
        zeroes = temp;
    }

    public boolean all() {
        return ones.size() == size;
    }

    public boolean one() {
        return ones.size() >= 1;
    }

    public int count() {
        return ones.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (ones.contains(i)) sb.append("1");
            else sb.append("0");
        }
        return sb.toString();
    }
}

/**
 * Your Bitset object will be instantiated and called as such:
 * Bitset obj = new Bitset(size);
 * obj.fix(idx);
 * obj.unfix(idx);
 * obj.flip();
 * boolean param_4 = obj.all();
 * boolean param_5 = obj.one();
 * int param_6 = obj.count();
 * String param_7 = obj.toString();
 */