//package com.google.challenges;

import java.util.*;

/*  Bunny Maze Puzzle
 */
public class Answer4_3 {

    public static int answer(int[][] maze) {
        // to save possible path branches to process in a breadth-first manner we need to implement a queue
        int area = maze.length * maze[1].length;
        pathQueue queue = new pathQueue(area);

        // create the starting maps with paths starting at the endpoints
        int [][] map = new int [maze.length][maze[1].length];
        queue.enqueue(new Path(0,0, map,"left", 1, 0));

        // create counter for the final step count, starting from the maximum number of nodes
        int finalPath = area;

        // process each node in queue, starting with 0,0
        System.out.println("Starting from 0,0!");
        while (!queue.isEmpty()) {
            Path path = queue.dequeue();
            if (map[path.yCoord][path.xCoord] != 0) { continue; }
            map[path.yCoord][path.xCoord] = path.step;
            System.out.println("Processing Path:");
            printMap(path.map);

            // if initial pass reaches the end, stop processing
            if (path.yCoord == maze.length-1 && path.xCoord == maze[1].length-1) {
                int finalCount = path.step - path.skip;
                if (finalCount < finalPath) {
                    finalPath = finalCount;
                }
                System.out.println("Exit Found!");
                break;
            }
            else {
                // calculate skip-able path
                // check left
                if (!path.prev.equals("left") && path.xCoord > 1 && path.map[path.yCoord][path.xCoord -2] != 0) {
                    int newSkip = path.step - (path.map[path.yCoord][path.xCoord -2]+2);
                    if (newSkip > path.skip) {
                        path.skip = newSkip;
                        }
                    System.out.println("Broke Left Wall, skip = "+newSkip+", max skip = "+path.skip);
                }
                // check right
                if (!path.prev.equals("right") && path.xCoord < maze[1].length-2 && path.map[path.yCoord][path.xCoord +2] != 0) {
                    int newSkip = path.step - (path.map[path.yCoord][path.xCoord +2]+2);
                    if (newSkip > path.skip) {
                        path.skip = newSkip;
                    }
                    System.out.println("Broke Right Wall, skip = "+newSkip+", max skip = "+path.skip);
                }
                // check down
                if (!path.prev.equals("down") && path.yCoord < maze.length-2 && path.map[path.yCoord +2][path.xCoord] != 0) {
                    int newSkip = path.step - (path.map[path.yCoord +2][path.xCoord]+2);
                    if (newSkip > path.skip) {
                        path.skip = newSkip;
                    }
                    System.out.println("Broke Lower Wall, skip = "+newSkip+", max skip = "+path.skip);
                }
                // check up
                if (!path.prev.equals("up") && path.yCoord > 1 && path.map[path.yCoord -2][path.xCoord] != 0) {
                    int newSkip = path.step - (path.map[path.yCoord -2][path.xCoord]+2);
                    if (newSkip > path.skip) {
                        path.skip = newSkip;
                    }
                    System.out.println("Broke Upper Wall, skip = "+newSkip+", max skip = "+path.skip);
                }

                // add all possible routes to the queue (except previous step)
                // check left
                if (!path.prev.equals("left") && path.xCoord != 0 && maze[path.yCoord][path.xCoord -1] == 0) {
                    // add step to the map and add node
                    System.out.println("Going Left!");
                    queue.enqueue(new Path(path.yCoord, path.xCoord -1, map, "right", path.step+1, path.skip));
                }
                // check right
                if (!path.prev.equals("right") && path.xCoord != maze[1].length-1 && maze[path.yCoord][path.xCoord +1] == 0) {
                    // add step to the map and add node
                    System.out.println("Going Right!");
                    queue.enqueue(new Path(path.yCoord, path.xCoord +1, map, "left", path.step+1, path.skip));
                }
                // check down
                if (!path.prev.equals("down") && path.yCoord != maze.length-1 && maze[path.yCoord +1][path.xCoord] == 0) {
                    // add step to the map and add node
                    System.out.println("Going Down!");
                    queue.enqueue(new Path(path.yCoord +1, path.xCoord, map, "up", path.step+1, path.skip));
                }
                // check up
                if (!path.prev.equals("up") && path.yCoord != 0 && maze[path.yCoord -1][path.xCoord] == 0) {
                    // add step to the map and add node
                    System.out.println("Going Up!");
                    queue.enqueue(new Path(path.yCoord -1, path.xCoord, map, "down", path.step+1, path.skip));
                }
            }
        }

        // if finish was not found, a wall must be broken
        if (finalPath == area) {
            System.out.println("Starting from end!");
            int [][] endMap = new int [maze.length][maze[1].length];
            queue.enqueue(new Path(maze.length-1,maze[1].length-1, endMap,"right", 1, 0));
            while (!queue.isEmpty()) {
                Path path = queue.dequeue();
                if (endMap[path.yCoord][path.xCoord] != 0) { continue; }
                endMap[path.yCoord][path.xCoord] = path.step;
                System.out.println("Processing Path:");
                printMap(path.map);

                // find path to first map
                // check left
                if (!path.prev.equals("left") && path.xCoord > 1 && map[path.yCoord][path.xCoord - 2] != 0 && maze[path.yCoord][path.xCoord - 1] == 1) {
                    int solution = path.step + (map[path.yCoord][path.xCoord - 2] + 1);
                    if (solution < finalPath) {
                        finalPath = solution;
                    }
                    System.out.println("Broke Left Wall, solution = " + solution + ", finalPath = " + finalPath);
                }
                // check left (corner-up)
                else if (!path.prev.equals("left") && path.xCoord > 0 && path.yCoord > 0 && map[path.yCoord - 1][path.xCoord - 1] != 0 && maze[path.yCoord][path.xCoord - 1] == 1) {
                    int solution = path.step + (map[path.yCoord - 1][path.xCoord - 1] + 1);
                    if (solution < finalPath) {
                        finalPath = solution;
                    }
                    System.out.println("Broke Left Wall, solution = " + solution + ", finalPath = " + finalPath);
                }
                // check left (corner-down)
                else if (!path.prev.equals("left") && path.xCoord > 0 && path.yCoord < map.length-1 && map[path.yCoord + 1][path.xCoord - 1] != 0 && maze[path.yCoord][path.xCoord - 1] == 1) {
                    int solution = path.step + (map[path.yCoord + 1][path.xCoord - 1] + 1);
                    if (solution < finalPath) {
                        finalPath = solution;
                    }
                    System.out.println("Broke Left Wall, solution = " + solution + ", finalPath = " + finalPath);
                }
                // check right
                if (!path.prev.equals("right") && path.xCoord < maze[1].length - 2 && map[path.yCoord][path.xCoord + 2] != 0 && maze[path.yCoord][path.xCoord + 1] == 1) {
                    int solution = path.step + (map[path.yCoord][path.xCoord + 2] + 1);
                    if (solution < finalPath) {
                        finalPath = solution;
                    }
                    System.out.println("Broke Right Wall, solution = " + solution + ", finalPath = " + finalPath);
                }
                // check right (corner-up)
                else if (!path.prev.equals("right") && path.xCoord < maze[1].length - 1 && path.yCoord > 0 && map[path.yCoord -1 ][path.xCoord + 1] != 0 && maze[path.yCoord][path.xCoord + 1] == 1) {
                    int solution = path.step + (map[path.yCoord - 1][path.xCoord + 1] + 1);
                    if (solution < finalPath) {
                        finalPath = solution;
                    }
                    System.out.println("Broke Right Wall, solution = " + solution + ", finalPath = " + finalPath);
                }
                // check right (corner-down)
                else if (!path.prev.equals("right") && path.xCoord < maze[1].length - 1 && path.yCoord < maze.length -1 && map[path.yCoord + 1][path.xCoord + 1] != 0 && maze[path.yCoord][path.xCoord + 1] == 1) {
                    int solution = path.step + (map[path.yCoord + 1][path.xCoord + 1] + 1);
                    if (solution < finalPath) {
                        finalPath = solution;
                    }
                    System.out.println("Broke Right Wall, solution = " + solution + ", finalPath = " + finalPath);
                }
                // check down
                if (!path.prev.equals("down") && path.yCoord < maze.length - 2 && map[path.yCoord + 2][path.xCoord] != 0 && maze[path.yCoord + 1][path.xCoord] == 1) {
                    int solution = path.step + (map[path.yCoord + 2][path.xCoord] + 1);
                    if (solution < finalPath) {
                        finalPath = solution;
                    }
                    System.out.println("Broke Lower Wall, solution = " + solution + ", finalPath = " + finalPath);
                }
                // check down (corner-left)
                else if (!path.prev.equals("down") && path.yCoord < maze.length - 1 && path.xCoord > 0 && map[path.yCoord + 1][path.xCoord - 1] != 0 && maze[path.yCoord + 1][path.xCoord] == 1) {
                    int solution = path.step + (map[path.yCoord + 1][path.xCoord - 1] + 1);
                    if (solution < finalPath) {
                        finalPath = solution;
                    }
                    System.out.println("Broke Lower Wall, solution = " + solution + ", finalPath = " + finalPath);
                }
                // check down (corner-right)
                else if (!path.prev.equals("down") && path.yCoord < maze.length - 1 && path.xCoord < maze[1].length - 1 && map[path.yCoord + 1][path.xCoord + 1] != 0 && maze[path.yCoord + 1][path.xCoord] == 1) {
                    int solution = path.step + (map[path.yCoord + 1][path.xCoord + 1] + 1);
                    if (solution < finalPath) {
                        finalPath = solution;
                    }
                    System.out.println("Broke Lower Wall, solution = " + solution + ", finalPath = " + finalPath);
                }
                // check up
                if (!path.prev.equals("up") && path.yCoord > 1 && map[path.yCoord - 2][path.xCoord] != 0 && maze[path.yCoord - 1][path.xCoord] == 1) {
                    int solution = path.step + (map[path.yCoord - 2][path.xCoord] + 1);
                    if (solution < finalPath) {
                        finalPath = solution;
                    }
                    System.out.println("Broke Upper Wall, solution = " + solution + ", finalPath = " + finalPath);
                }
                // check up (corner-left)
                else if (!path.prev.equals("up") && path.yCoord > 0 && path.xCoord > 0 && map[path.yCoord - 1][path.xCoord - 1] != 0 && maze[path.yCoord - 1][path.xCoord] == 1) {
                    int solution = path.step + (map[path.yCoord - 1][path.xCoord - 1] + 1);
                    if (solution < finalPath) {
                        finalPath = solution;
                    }
                    System.out.println("Broke Upper Wall, solution = " + solution + ", finalPath = " + finalPath);
                }
                // check up (corner-right)
                else if (!path.prev.equals("up") && path.yCoord > 0 && path.xCoord > map[1].length && path.map[path.yCoord - 1][path.xCoord + 1] != 0 && maze[path.yCoord - 1][path.xCoord] == 1) {
                    int solution = path.step + (map[path.yCoord - 1][path.xCoord + 1] + 1);
                    if (solution < finalPath) {
                        finalPath = solution;
                    }
                    System.out.println("Broke Upper Wall, solution = " + solution + ", finalPath = " + finalPath);
                }

                // add all possible routes to the queue (except previous step)
                // check left
                if (!path.prev.equals("left") && path.xCoord != 0 && maze[path.yCoord][path.xCoord - 1] == 0) {
                    // add step to the map and add node
                    System.out.println("Going Left!");
                    queue.enqueue(new Path(path.yCoord, path.xCoord - 1, path.map, "right", path.step + 1, path.skip));
                }
                // check right
                if (!path.prev.equals("right") && path.xCoord != maze[1].length - 1 && maze[path.yCoord][path.xCoord + 1] == 0) {
                    // add step to the map and add node
                    System.out.println("Going Right!");
                    queue.enqueue(new Path(path.yCoord, path.xCoord + 1, path.map, "left", path.step + 1, path.skip));
                }
                // check down
                if (!path.prev.equals("down") && path.yCoord != maze.length - 1 && maze[path.yCoord + 1][path.xCoord] == 0) {
                    // add step to the map and add node
                    System.out.println("Going Down!");
                    queue.enqueue(new Path(path.yCoord + 1, path.xCoord, path.map, "up", path.step + 1, path.skip));
                }
                // check up
                if (!path.prev.equals("up") && path.yCoord != 0 && maze[path.yCoord - 1][path.xCoord] == 0) {
                    // add step to the map and add node
                    System.out.println("Going Up!");
                    queue.enqueue(new Path(path.yCoord - 1, path.xCoord, path.map, "down", path.step + 1, path.skip));
                }

            }
        }
        System.out.println("Queue Emptied!");
        return finalPath;
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
            start++;
            if (start == pathArray.length) {
                start = 0;
            }
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
    }

    private static void printMap(int [][] map) {
        for (int[] layer : map) {
            System.out.println(Arrays.toString(layer));
        }
    }
}

