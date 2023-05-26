import java.util.*;

class Buffer {
    int id;
    boolean delayWrite;
    public Buffer(int id){
        this.id = id;
        this.delayWrite = false;
    }
}

public class GetBlk {
    private static int hashValue(int id, int size){
        return id % size;
    }
    private static void addBlock(HashMap<Integer, ArrayList<Buffer>> hashQueue,  ArrayList<Buffer> freeList, int size){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the block id: ");
        int id = sc.nextInt();
        int key = hashValue(id, size);

        System.out.print("\nIs the entered block busy?(y | n): ");
        char busyStatus = sc.next().charAt(0);
        Buffer buffer = new Buffer(id);
        
        // scenario - 3
        if(busyStatus == 'n'){
            System.out.print("\nIs the entered block delayed write?(y | n):  ");
            char delayedStatus = sc.next().charAt(0);
            if(delayedStatus != 'y'){
                freeList.add(buffer);
            }
        }
        
        ArrayList<Buffer> temp = hashQueue.get(key);
        temp.add(buffer);
    }
    private static void display(HashMap<Integer, ArrayList<Buffer>> hashQueue , ArrayList<Buffer> freeList , int size){
        System.out.println("============ HASH QUEUE ============");
        for(int i = 0; i < size; i++){
            ArrayList<Buffer> temp = hashQueue.get(i);
            System.out.print(i + " -> [ ");
            for(Buffer buffer : temp){
                System.out.print(buffer.id + " ");
            }
            System.out.println(" ] ");
        }
        System.out.println("============ FREE LIST ============");
        for(Buffer buffer : freeList){
            System.out.print(buffer.id + " ");
        }
        System.out.println("\n");
    }

    private static void allocateBuffer(HashMap<Integer, ArrayList<Buffer>> hashQueue, ArrayList<Buffer> freeList, int size){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the id: ");
        int id = sc.nextInt();
        int key = hashValue(id, size);
        ArrayList<Buffer> temp = hashQueue.get(key);
        
        // scenario - 4 - empty free list

        if(freeList.isEmpty()) {
            System.out.println("Currently all buffers are busy....");
            return;
        }
        
        // scenario - 1 - if the element exist in free list then remove the block from free list and make it busy
        for(int i = 0; i < freeList.size(); i++){
            Buffer buffer = freeList.get(i);
            if(buffer.id == id){
                freeList.remove(i);
                return;
            }
        }
        
        // if above block not found then search in hashqueue and if found in hashqueue then consider block is busy
        int posOnHashQueue = -1;
        
        for(int i = 0; i < temp.size(); i++){
            if(temp.get(i).id == id){
                posOnHashQueue = i;
                System.out.println("Block is busy...");
                return;
            }
        }

        // scenario - 2 - if not found in hashqueue then remove the first block in freelist and allocate the block in hashqueue

        if(!freeList.isEmpty()) freeList.remove(0);
        temp.add(new Buffer(id));

        

    }
    
    private static void brelse(HashMap<Integer, ArrayList<Buffer>> hashQueue, ArrayList<Buffer> freeList, int size){
    	Scanner sc = new Scanner(System.in);
    	System.out.print("Enter the id to release: ");
    	int id = sc.nextInt();
    	int key = id % size;
    	ArrayList<Buffer> hTemp = hashQueue.get(key);
    	int posInQueue = -1;
    	int posInFreeList = -1;
    	
    	for(int i = 0; i < hTemp.size(); i++){
    		Buffer buffer = hTemp.get(i);
    		if(buffer.id == id){
    			posInQueue = i;
    			break;
    		}
    	}
    	for(int i = 0; i < freeList.size(); i++){
    		Buffer buffer  = freeList.get(i);
    		if(buffer.id == id){
    			posInFreeList = i;
    			break;
    		}
    	}
    	
    	if(posInQueue != -1 && posInFreeList == -1){
    		freeList.add(hTemp.get(posInQueue));
    		System.out.println("\n--> Buffer made free!!!\n");
    		return;
    	}
    	
    	if(posInQueue != -1 && posInFreeList != -1){
    		System.out.println("\n--> Block is already free!!!\n");
    		return;
    	}
    	if(posInQueue == -1 && posInFreeList == -1){
    		System.out.println("\n--> Block is not allocated !!!\n");
    		return;
    	}
    }

    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Buffer>> hashQueue = new HashMap<Integer, ArrayList<Buffer>>();
        ArrayList<Buffer> freeList = new ArrayList<Buffer>();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the size of hash queue: ");
        int size = sc.nextInt();

        for(int i = 0; i < size; i++){
            hashQueue.put(i, new ArrayList<Buffer>());
        }

        while(true){
            System.out.println("=================== MENU =================");
            System.out.println("1. Add block(to simulate initial hashqueue and freelist)\n2. Display\n3. Allocate Buffer (To simulate Scenarios)\n4. Empty FreeList(To simulate 4th scenario use this prior)\n5. brelse\n6. EXIT");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch(choice){
                case 1: addBlock(hashQueue, freeList, size);
                        break;
                case 2: display(hashQueue, freeList, size);
                        break;
                case 3: allocateBuffer(hashQueue, freeList, size);
                        break;
                case 4: freeList.clear();
                 	 break;
                case 5: brelse(hashQueue, freeList, size);
                	 break;
                case 6: return;
                default: System.out.println("Invalid choice!!!");
            }
        }        
    }
}