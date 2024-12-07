import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Best Fit Memory Allocation "+"\n==================================================");

        Scanner scanner = new Scanner(System.in);

        //Input the number of blocks and their sizes
        System.out.println("Enter the Number Of Blocks :");
        int numBlock = scanner.nextInt();
        int[] blockSizes = new int[numBlock];
        int[] originalBlockSizes = new int[numBlock]; // To track original sizes for fragmentation

        System.out.println("Enter the sizes of the memory blocks:");

        for (int i = 0; i < numBlock; i++) {
            System.out.println("Block" + (i + 1) + ":");
            blockSizes[i] = scanner.nextInt();
            originalBlockSizes[i] = blockSizes[i];
        }

        // Input the number of processes and their sizes
        System.out.println("Enter the Number Of Processes :");
        int numProcess = scanner.nextInt();
        int[] processSize = new int[numProcess];

        System.out.println("Enter the sizes of the Processes:");
        for (int j = 0; j < numProcess; j++) {
            System.out.println("Process" + (j + 1) + ":");
            processSize[j] = scanner.nextInt();

        }

        //Best fit allocation
        int[] bestFitAllocation = new int[numProcess];
        for (int i = 0; i < numProcess; i++) {
            bestFitAllocation[i] = -1; // Initialize as not allocated
            int bestFitIndex = -1;
            for (int j = 0; j < numBlock; j++) {
                if (blockSizes[j] >= processSize[i]) {
                    if (bestFitIndex == -1 || blockSizes[j] < blockSizes[bestFitIndex]) {
                        bestFitIndex = j;

                    }

                }
            }

            // Allocate the best-fit block to the process
            if (bestFitIndex != -1) {
                bestFitAllocation[i] = bestFitIndex;
                blockSizes[bestFitIndex] -= processSize[i];
            }
        }


        // Output the allocation result with fragmentation
        System.out.println("=================================================="+"\nProcess Allocation:");
        for (int i = 0; i < numProcess; i++) {
            if (bestFitAllocation[i] != -1) {

                int allocatedBlock = bestFitAllocation[i];
                int fragmentation = blockSizes[allocatedBlock];

                System.out.println("Process " + (i + 1) + " of size " + processSize[i]
                        + " is allocated to Block " + (allocatedBlock + 1) + " - Fragmentation of Block " + (allocatedBlock + 1) + ": " + fragmentation + " units");
            } else {
                System.out.println("Process " + (i + 1) + " of size " + processSize[i] + " is not allocated.");
            }
        }

    }
}