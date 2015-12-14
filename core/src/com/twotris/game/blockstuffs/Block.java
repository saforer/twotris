package com.twotris.game.blockstuffs;

/**
 * Created by Forer on 12/12/2015.
 */
public class Block {
    public int xTopLeft;
    public int yTopLeft;
    public int[][] shape;

    public Block() {
        this(BlockTypes.values()[(int) (Math.random()*BlockTypes.values().length)]);
    }

    public Block (BlockTypes b) {
        System.out.println("THE TYPE OF THE BLOCK IS " + b.toString());
        shape = getShape(b);
        xTopLeft = 4;
        yTopLeft = 7;
    }

    public Block (Block b) {
        this.xTopLeft = b.xTopLeft;
        this.yTopLeft = b.yTopLeft;
        this.shape = b.shape;
    }

    int[][] getShape() {
        return getShape(BlockTypes.values()[(int) (Math.random()*BlockTypes.values().length)]);
    }

    int[][] getShape(BlockTypes b) {
        int[][] shape = new int[4][4];


        switch (b) {
            default:
            case square:
                shape[1][1] = 1;
                shape[1][2] = 1;
                shape[2][1] = 1;
                shape[2][2] = 1;
                break;
            case line:
                shape[1][3] = 2;
                shape[1][2] = 2;
                shape[1][1] = 2;
                shape[1][0] = 2;
                break;
            case t:
                shape[1][3] = 3;
                shape[0][2] = 3;
                shape[1][2] = 3;
                shape[2][2] = 3;
                break;
            case s:
                shape[1][3] = 7;
                shape[2][3] = 7;
                shape[0][2] = 7;
                shape[1][2] = 7;
                break;
            case z:
                shape[0][3] = 4;
                shape[1][3] = 4;
                shape[1][2] = 4;
                shape[2][2] = 4;
                break;
            case j:
                shape[1][3] = 5;
                shape[1][2] = 5;
                shape[1][1] = 5;
                shape[0][1] = 5;
                break;
            case l:
                shape[0][3] = 6;
                shape[0][2] = 6;
                shape[1][1] = 6;
                shape[0][1] = 6;
                break;
        }


        return shape;
    }
}
