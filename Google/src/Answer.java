//package com.google.challenges;

/*  Bunny Maze Puzzle
    */
public class Answer {
    static int minLength = 0; // holds counter for breadth first search

    public static int answer(int[][] maze) {
        // in order to do breadth-first search we need to implement a queue
        nodeQueue queue = new nodeQueue(maze.length, maze[1].length);

        // move in all possible directions.
        while (!queue.isEmpty()) {
            Node toProcess = queue.dequeue();
            switch (toProcess.prev) {
                case "right": { moveRight(toProcess, maze); break;}
                case "down": { moveDown(toProcess, maze); break;}
                case "up": { moveUp(toProcess, maze); break;}
                case "left": { moveLeft(toProcess, maze); break;}
            }
        }
        return minLength;
    }

    // CASE 1: RIGHT
    private static void moveRight(Node node, int[][] maze) {
        if (minLength != 0) {
            // check up
            if (minLength != 0 && node.xCoord != 0) {
                if ()
            }
            // check right

            // check down
        }
    }

    // CASE 2: DOWN
    private static void moveDown(Node node, int[][] maze) {

    }

    // CASE 3: LEFT (NOT AT BEGINNING)
    private static void moveLeft(Node node, int[][] maze) {

    }

    // CASE 4: UP (NOT AT BEGINNING)
    private static void moveUp(Node node, int[][] maze) {

    }

    //System.out.println(String.valueOf(queue.length));

    private static class nodeQueue {
        private Node[] nodeArray;
        private int start = 0;
        private int end = 0;

        nodeQueue(int length, int width) {
            nodeArray = new Node[(length * width)+1];
            enqueue(new Node(0,0, true,"right"));
        }

        public Boolean isEmpty() { return start == end; }

        public void enqueue(Node node) {
            nodeArray[start] = node;
            start++;
        }

        public Node dequeue() {
            Node node = null;
            if (!isEmpty()) {
                node = nodeArray[end];
                end++;
            }
            return node;
        }
    }

    private static class Node {
        public int xCoord;
        public int yCoord;
        public Boolean wall;
        public String prev;

        Node(int x, int y, Boolean w, String p) {
            xCoord = x;
            yCoord = y;
            prev = p;
            wall = w;
        }
    }
}

