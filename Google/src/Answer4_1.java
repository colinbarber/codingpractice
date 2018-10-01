//package com.google.challenges;

/*  Bunny Maze Puzzle
    */
public class Answer4_1 {
    private static int minLength = 0; // holds counter for breadth first search

    public static int answer(int[][] maze) {
        // in order to do breadth-first search we need to implement a queue
        nodeQueue queue = new nodeQueue(maze.length, maze[1].length);

        // process each node in queue
        while (minLength == 0 && !queue.isEmpty()) {
            Node node = queue.dequeue();

            System.out.println("Processing Path: ("+String.valueOf(node.xCoord)+", "+String.valueOf(node.yCoord)+")");
            // if exit node, set minLength and skip processing
            if (node.xCoord == maze.length-1 && node.yCoord == maze[1].length-1) {
                minLength = node.step;
                System.out.println("Exit Found!");
            }
            // if other node, add all possible routes to the queue (except previous step)
            else {
                // check up
                if (!node.prev.equals("up") && node.yCoord != 0) {
                    if (maze[node.xCoord][node.yCoord-1] == 0) {
                        System.out.println("Path: "+String.valueOf(node.step)+" Going Up!");
                        queue.enqueue(new Node(node.xCoord, node.yCoord-1, node.wall, "down", node.step+1));
                    }
                    else if (node.wall && node.yCoord != 1 && maze[node.xCoord][node.yCoord-2] == 0) {
                        System.out.println("Path: "+String.valueOf(node.step)+" Going Up! (broke wall)");
                        queue.enqueue(new Node(node.xCoord, node.yCoord-1, false, "down", node.step+1));
                    }
                }
                // check down
                if (!node.prev.equals("down") && node.yCoord != maze[1].length-1) {
                    if (maze[node.xCoord][node.yCoord+1] == 0) {
                        System.out.println("Path: "+String.valueOf(node.step)+" Going Down!");
                        queue.enqueue(new Node(node.xCoord, node.yCoord+1, node.wall, "up", node.step+1));
                    }
                    else if (node.wall && node.yCoord != maze[1].length-2 && maze[node.xCoord][node.yCoord+2] == 0) {
                        System.out.println("Path: "+String.valueOf(node.step)+" Going Down! (broke wall)");
                        queue.enqueue(new Node(node.xCoord, node.yCoord+1, false, "up", node.step+1));
                    }
                }
                // check right
                if (!node.prev.equals("right") && node.xCoord != maze.length-1) {
                    if (maze[node.xCoord+1][node.yCoord] == 0) {
                        System.out.println("Path: "+String.valueOf(node.step)+" Going Right!");
                        queue.enqueue(new Node(node.xCoord+1, node.yCoord, node.wall, "left", node.step+1));
                    }
                    else if (node.wall && node.xCoord != maze.length-2 && maze[node.xCoord+2][node.yCoord] == 0) {
                        System.out.println("Path: "+String.valueOf(node.step)+" Going Right! (broke wall)");
                        queue.enqueue(new Node(node.xCoord+1, node.yCoord, false, "left", node.step+1));
                    }
                }
                // check left
                if (!node.prev.equals("left") && node.xCoord != 0) {
                    if (maze[node.xCoord-1][node.yCoord] == 0) {
                        System.out.println("Path: "+String.valueOf(node.step)+" Going left!");
                        queue.enqueue(new Node(node.xCoord-1, node.yCoord, node.wall, "right", node.step+1));
                    }
                    else if (node.wall && node.xCoord != 1 && maze[node.xCoord-2][node.yCoord] == 0) {
                        System.out.println("Path: "+String.valueOf(node.step)+" Going Left! (broke wall)");
                        queue.enqueue(new Node(node.xCoord-1, node.yCoord, false, "right", node.step+1));
                    }
                }
            }

        }
        System.out.println("Queue Emptied!");
        return minLength;
    }

    // circular queue
    private static class nodeQueue {
        private Node[] nodeArray;
        private int start = 0;
        private int end = 0;

        nodeQueue(int length, int width) {
            int square = (length * width);
            nodeArray = new Node[square * square * square];
            System.out.println("new queue length: "+String.valueOf(nodeArray.length));
            enqueue(new Node(0,0, true,"left", 1));
        }

        Boolean isEmpty() { return start == end; }

        void enqueue(Node node) {
            nodeArray[start] = node;
            System.out.println("Queued node at: "+node.coords());
            start++;
            if (start == nodeArray.length) {
                start = 0;
            }
            System.out.println(startFinish());
        }

        Node dequeue() {
            Node node = null;
            if (!isEmpty()) {
                node = nodeArray[end];
                end++;
                if (end == nodeArray.length) {
                    end = 0;
                }
            }
            System.out.println(startFinish());
            return node;
        }

        String startFinish() {
            return String.valueOf("s = "+start+", e = "+end);
        }
    }

    private static class Node {
        int xCoord;
        int yCoord;
        Boolean wall; // true if wall can be broken
        String prev;
        int step;

        Node(int x, int y, Boolean wa, String pr, int st) {
            xCoord = x;
            yCoord = y;
            prev = pr;
            wall = wa;
            step = st;
        }

        String coords() {
            return String.valueOf(xCoord+", "+yCoord);
        }
    }
}

