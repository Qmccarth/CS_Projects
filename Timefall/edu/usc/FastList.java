
package Timefall.edu.usc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FastList<AnyType extends IntegerComparable>
{
	//each node has between one and eight next references
	public static final int MAX_LEVEL = 7;
	private Node<AnyType> header;
	private int[] levels;
	private int toplev;

	
public FastList(){ //create 

	toplev = 0;
	header = new Node<AnyType>(MAX_LEVEL, null);
	levels = new int[MAX_LEVEL];
	createlevels();
	}
	
	public void createlevels() {
		levels[MAX_LEVEL-1] = (2 << (MAX_LEVEL-1) -1);
		for(int i = MAX_LEVEL-2, j= 0; i >= 0 ;i--,j++) {
				levels[i] = levels[i+1] - (2 << j);
		}
}
	public int chooselevel() {
		Random rd = new Random();
		int i, r = Math.abs(rd.nextInt()) % levels[MAX_LEVEL-1] + 1;
        for (i = 1; i < MAX_LEVEL; i++)
            if (r < levels[i])
                return i-1; // return a level < the highest level;
        return i-1;
}
	
	public ArrayList<AnyType> get()
	{
		Node<AnyType> curr = header;
		ArrayList<AnyType> a = new ArrayList();
		while(curr.next[0] !=null) {
			if(curr!=null) a.add(curr.data);
			curr = curr.next[0];
		}
		return a;
	}
 /**
	*  Returns the string contents of the list
	*  The method traverses the level 0 references
	*/
	public String toString()
	{
		Node<AnyType> curr = header;
		String buf = "";
		while(curr.next[0] !=null) {
			buf += curr.next[0].data.toString() + "\n";
			curr = curr.next[0];
		}
		return buf;
	}

/**
	*  Returns the string contents of the list at a given level
	*  The method traverses nodes at given level
	*/
	public String toString(int level)
	{
		Node<AnyType> curr = header;
		String buf = "";
		while(curr.next[level] !=null) {
			buf += curr.next[level].data.toString() + "\n";
			curr = curr.next[level];
		}
		return buf;
	}


 /**
	*  Returns true if the given value is stored in the list, otherwise false.
	*  The search begins at the topmost reference of the header
	*/
	public AnyType contains(int toSearch)
	{
		int level =toplev;
		Node<AnyType> currNode = header;	
		for(int i = level; i>= 0; i--) { 
			while(currNode!= null && currNode.next[i] != null && currNode.next[i].data.compareTo(toSearch) < 0){
				currNode = currNode.next[i];
			}		
		}
		currNode = currNode.next[0];
		if(currNode.data.compareTo(toSearch) ==0) return currNode.data;
		else return null;		
}

 /**
	*  Returns true if the given value is stored in the list, otherwise false.
	*  The search begins at the topmost reference of the header
	*/
	public AnyType contains(AnyType toSearch)
	{
		int level = toplev;
		Node<AnyType> currNode = header;		
		for(int i = level; i>= 0; i--) {
			while(currNode!= null && currNode.next[i] != null && toSearch.compareTo(currNode.next[level].data) > 0){
				currNode = currNode.next[i];
			}					
		}
		currNode = currNode.next[0];
		if(currNode.data.compareTo(toSearch) ==0) return currNode.data;
		else return null;		
}

 /**
	*  Inserts a given value into the list at random level
	*  In order to insert a new node into the list we must maintain an array
	*  of nodes whose next references must be updated.
	*/
	public void insert(AnyType toInsert)
	{
		int level = chooselevel() ;
		insert(toInsert, level);
}

 /**
	*  Inserts a given value into the list at a given level
	*  The level must not exceed MAX_LEVEL.
	*/
	public void insert(AnyType toInsert, int level)
	{	
		if(level > toplev) {
			Node<AnyType> tempNode = header;
			header = new Node(level, tempNode.data);
			 for (int i = 0; i <= toplev; i++)  header.next[i] = tempNode.next[i];
			 toplev = level;
		}
		Node<AnyType> currNode = header;
		Node<AnyType> inserter = new Node(level, toInsert);
		int currlev = level;
		for(int i = currlev; i >=0;i--) {
			while (currNode.next[i] != null && toInsert.compareTo(currNode.next[i].data) > 0) {
				currNode = currNode.next[i];
			}
			inserter.next[i] = currNode.next[i];
			currNode.next[i] = inserter;
		}
		
	}

	

 /**
	*  Deletes a node that contains the given value.
	*  In order to delete a node we must maintain an array
	*  of nodes whose next references must be updated.
	*/
	public void delete(AnyType toDelete)
	{
		Node<AnyType> currNode = header;
		Node<AnyType>[] update = new Node[MAX_LEVEL+1];
		int level = toplev;
		
		for(int i = toplev; i>= 0; i--) {
			while(currNode!= null && currNode.next[i] != null && currNode.next[i].data.compareTo(toDelete) < 0){
				currNode = currNode.next[i];
			}		
			update[i] = currNode;
		}
		currNode = currNode.next[0];
		
		if(currNode != null && currNode.data.compareTo(toDelete) == 0) { 
	        for(int i=0;i<=toplev;i++)  { 
	            if(update[i].next[i] != currNode) 
	                break; 
	  
	            update[i].next[i] = currNode.next[i]; 
	        } 
	  
	        // Remove levels having no elements  
	        while(level>0 && header.next[level].data.compareTo(toDelete) == 0)   level--; 
	}
	
}
	
	private static class Node<AnyType>
	{
	  public AnyType data;
	  public Node<AnyType>[] next;
	  public Node(int randLevel, AnyType data)
	  {
	    next = new Node[randLevel + 1];
	    this.data = data;
	  }
	}
	
}



