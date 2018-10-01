//package com.google.challenges;

import java.util.*;

/*  Bunny Maze Puzzle
 */
public class Answer4_2 {

    public static int answer(int[][] maze) {
        int area = maze.length * maze[1].length;
        // to save possible path branches to process in a breadth-first manner we need to implement a queue
        pathQueue queue = new pathQueue(area);
        // in case of no clear path we need to store potential paths through walls in a stack
        pathStack stack = new pathStack(area);

        // create the starting map with 1 at 0,0
        int [][] initMap = new int [maze.length][maze[1].length];
        initMap [0][0] = 1;
        queue.enqueue(new Path(0,0, initMap,"left", 1, 0));

        // create counter for the final step count, starting from the maximum number of nodes
        int pathCount = area;

        // process each node in queue, starting with 0,0
        while (!queue.isEmpty()) {
            Path path = queue.dequeue();
            System.out.println("Processing Path:");
            printMap(path.map);

            // if exit node, set pathCount and skip processing
            if (path.yCoord == maze.length-1 && path.xCoord == maze[1].length-1) {
                int finalCount = path.step - path.skip;
                if (finalCount < pathCount) {
                    pathCount = finalCount;
                }
                System.out.println("Exit Found!");
                break;
            }
            else {
                // calculate skip-able path from previous and store potential paths to break through in case of no clear path
                // check left
                if (!path.prev.equals("left") && path.xCoord > 1) {
                    if (path.map[path.yCoord][path.xCoord -2] != 0) {
                        int newSkip = path.step - (path.map[path.yCoord][path.xCoord -2]+2);
                        if (newSkip > path.skip) {
                            path.skip = newSkip;
                        }
                        System.out.println("Broke Left Wall, skip = "+newSkip+", max skip = "+path.skip);
                    }
                    else if (maze[path.yCoord][path.xCoord -2] == 0) {
                        // add step to the map and add node
                        System.out.println("Potential Left Path Through Wall!");
                        int [][] leftMap = newMap(path.map);
                        leftMap[path.yCoord][path.xCoord -2] = path.step+2;
                        //System.out.println("New Map Generated: ");
                        //printMap(leftMap);
                        stack.push(new Path(path.yCoord, path.xCoord -2, leftMap, "right", path.step+2, 0));
                    }
                }
                // check right
                if (!path.prev.equals("right") && path.xCoord < maze[1].length-2) {
                    if (path.map[path.yCoord][path.xCoord +2] != 0) {
                        int newSkip = path.step - (path.map[path.yCoord][path.xCoord +2]+2);
                        if (newSkip > path.skip) {
                            path.skip = newSkip;
                        }
                        System.out.println("Broke Right Wall, skip = "+newSkip+", max skip = "+path.skip);
                    }
                    else if (maze[path.yCoord][path.xCoord +2] == 0) {
                        // add step to the map and add node
                        System.out.println("Potential Right Path Through Wall!");
                        int [][] rightMap = newMap(path.map);
                        rightMap[path.yCoord][path.xCoord +2] = path.step+2;
                        //System.out.println("New Map Generated: ");
                        //printMap(leftMap);
                        stack.push(new Path(path.yCoord, path.xCoord +2, rightMap, "left", path.step+2, 0));
                    }
                }
                // check down
                if (!path.prev.equals("down") && path.yCoord < maze.length-2) {
                    if (path.map[path.yCoord +2][path.xCoord] != 0) {
                        int newSkip = path.step - (path.map[path.yCoord +2][path.xCoord]+2);
                        if (newSkip > path.skip) {
                            path.skip = newSkip;
                        }
                        System.out.println("Broke Lower Wall, skip = "+newSkip+", max skip = "+path.skip);
                    }
                    else if (maze[path.yCoord +2][path.xCoord] == 0) {
                        // add step to the map and add node
                        System.out.println("Potential Right Path Through Wall!");
                        int [][] downMap = newMap(path.map);
                        downMap[path.yCoord+2][path.xCoord] = path.step+2;
                        //System.out.println("New Map Generated: ");
                        //printMap(leftMap);
                        stack.push(new Path(path.yCoord+2, path.xCoord, downMap, "up", path.step+2, 0));
                    }
                }
                // check up
                if (!path.prev.equals("up") && path.yCoord > 1) {
                    if (path.map[path.yCoord -2][path.xCoord] != 0) {
                        int newSkip = path.step - (path.map[path.yCoord -2][path.xCoord]+2);
                        if (newSkip > path.skip) {
                            path.skip = newSkip;
                        }
                        System.out.println("Broke Upper Wall, skip = "+newSkip+", max skip = "+path.skip);
                    }
                    else if (maze[path.yCoord -2][path.xCoord] == 0) {
                        // add step to the map and add node
                        System.out.println("Potential Right Path Through Wall!");
                        int [][] upMap = newMap(path.map);
                        upMap[path.yCoord-2][path.xCoord] = path.step+2;
                        //System.out.println("New Map Generated: ");
                        //printMap(leftMap);
                        stack.push(new Path(path.yCoord-2, path.xCoord, upMap, "down", path.step+2, 0));
                    }
                }

                // add all possible routes to the queue (except previous step)
                // check left
                if (!path.prev.equals("left") && path.xCoord != 0 && maze[path.yCoord][path.xCoord -1] == 0) {
                    // add step to the map and add node
                    System.out.println("Going Left!");
                    int [][] leftMap = newMap(path.map);
                    leftMap[path.yCoord][path.xCoord -1] = path.step+1;
                    //System.out.println("New Map Generated: ");
                    //printMap(leftMap);
                    queue.enqueue(new Path(path.yCoord, path.xCoord -1, leftMap, "right", path.step+1, path.skip));
                }
                // check right
                if (!path.prev.equals("right") && path.xCoord != maze[1].length-1 && maze[path.yCoord][path.xCoord +1] == 0) {
                    // add step to the map and add node
                    System.out.println("Going Right!");
                    int[][] rightMap = newMap(path.map);
                    rightMap[path.yCoord][path.xCoord +1] = path.step+1;
                    //System.out.println("New Map Generated: ");
                    //printMap(rightMap);
                    queue.enqueue(new Path(path.yCoord, path.xCoord +1, rightMap, "left", path.step+1, path.skip));
                }
                // check down
                if (!path.prev.equals("down") && path.yCoord != maze.length-1 && maze[path.yCoord +1][path.xCoord] == 0) {
                    // add step to the map and add node
                    System.out.println("Going Down!");
                    int[][] downMap = newMap(path.map);
                    downMap[path.yCoord +1][path.xCoord] = path.step+1;
                    //System.out.println("New Map Generated: ");
                    //printMap(downMap);
                    queue.enqueue(new Path(path.yCoord +1, path.xCoord, downMap, "up", path.step+1, path.skip));
                }
                // check up
                if (!path.prev.equals("up") && path.yCoord != 0 && maze[path.yCoord -1][path.xCoord] == 0) {
                    // add step to the map and add node
                    System.out.println("Going Up!");
                    int[][] upMap = newMap(path.map);
                    upMap[path.yCoord -1][path.xCoord] = path.step+1;
                    //System.out.println("New Map Generated: ");
                    //printMap(upMap);
                    queue.enqueue(new Path(path.yCoord -1, path.xCoord, upMap, "down", path.step+1, path.skip));
                }
            }
            // if exit has not been found, stack looking through potential walls
            if (queue.isEmpty()) {
                queue.enqueue(stack.pop());
            }
        }
        //System.out.println("Queue Emptied!");
        return pathCount;
    }

    // circular queue
    private static class pathQueue {
        private Path[] pathArray;
        private int start = 0;
        private int end = 0;

        pathQueue(int length) {
            pathArray = new Path[length];
            System.out.println("new queue length: "+length);
        }

        Boolean isEmpty() { return start == end; }

        void enqueue(Path path) {
            pathArray[start] = path;
            //System.out.println("Queued path at: "+ path.coords());
            start++;
            if (start == pathArray.length) {
                start = 0;
            }
            //System.out.println(startFinish());
        }

        Path dequeue() {
            Path path = null;
            if (!isEmpty()) {
                path = pathArray[end];
                end++;
                if (end == pathArray.length) {
                    end = 0;
                }
            }
            //System.out.println(startFinish());
            return path;
        }

        String startFinish() {
            return String.valueOf("s = "+start+", e = "+end);
        }
    }

    // stack to store potential paths to break through if there is no clear path to exit
    private static class pathStack {
        private Path[] pathArray;
        private int index = -1;

        pathStack(int length) {
            pathArray = new Path[length];
            System.out.println("new stack length: "+length);
        }

        Boolean isEmpty() { return index == -1; }

        void push(Path path) {
            index++;
            pathArray[index] = path;
        }

        Path pop() {
            Path path = null;
            if (!isEmpty()) {
                path = pathArray[index];
                index--;
            }
            return path;
        }
    }

    private static class Path {
        int yCoord;
        int xCoord;
        int [][] map;
        String prev;
        int step;
        int skip;

        Path(int y, int x, int [][] ma, String pr, int st, int sk) {
            yCoord = y;
            xCoord = x;
            map = ma;
            prev = pr;
            step = st;
            skip = sk;
        }

        String coords() {
            return String.valueOf(yCoord +", "+ xCoord);
        }
    }
    private static void printMap(int [][] map) {
        for (int[] layer : map) {
            System.out.println(Arrays.toString(layer));
        }
    }

    private static int[][] newMap(int[][] map) {
        int[][] newMap = new int[map.length][map[1].length];
        for (int i = 0; i < map.length; i++) {
            newMap[i] = Arrays.copyOf(map[i], map[i].length);
        }
        return newMap;
    }

}

