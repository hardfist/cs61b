
public class DoubleChain {
	
	private DNode head;
	
	public DoubleChain(double val) {
		/* your code here. */
		head = new DNode(null,val,null);
		head.prev = head.next = head;
	}
	public static DoubleChain list(int ...args)
	{
	    DoubleChain lst = new DoubleChain(args[0]);
	    for(int i=1;i<args.length;i++)
	    {
	        lst.insertBack(args[i]);
	    }
	    return lst;
	}
	public DNode getFront() {
		return head;
	}

	/** Returns the last item in the DoubleChain. */		
	public DNode getBack() {
		/* your code here */
		return head.prev;
	}
	
	/** Adds D to the front of the DoubleChain. */	
	public void insertFront(double d) {
		/* your code here */
	    if( head == null)
	    {
	        head = new DNode(null,d,null);
	        head.prev = head.next = head;
	    }
	    else
	    {
	        DNode cur = new DNode(head.prev,d,head);
	        head.prev.next=cur;
	        head.prev = cur;
	        head = cur;
	    }
	}
	public boolean empty()
	{
	    return head == null;
	}
	/** Adds D to the back of the DoubleChain. */	
	public void insertBack(double d) {
		/* your code here */
	    if (head == null)
	    {
	        head = new DNode(null,d,null);
	        head.prev = head.next = head;
	    }
	    else{
	        DNode cur = new DNode(head.prev,d,head);
	        head.prev.next = cur;
	        head.prev = cur;
	    }
	}
	
	/** Removes the last item in the DoubleChain and returns it. 
	  * This is an extra challenge problem. */
	public DNode deleteBack() {
		/* your code here */
	    if(head == null)
	    {
	        return null;
	    }
	    else if(head.prev == head)
	    {
	        DNode tmp = head;
	        head = null;
	        tmp.prev = null;
	        tmp.next = null;
	        return tmp;
	    }
	    else
	    {
	        DNode back = head.prev;
	        DNode prev = head.prev.prev;
	        prev.next = head;
	        head.prev = prev;
	        back.prev = null;
	        back.next = null;
	        return back;
	    }
	}
	
	/** Returns a string representation of the DoubleChain. 
	  * This is an extra challenge problem. */
	public String toString() {
		/* your code here */
		String s="<[";
		DNode itr = head;
		if(itr == null)
		{
		    return "<[]>";
		}
		s += itr.val+"";
		itr = itr.next;
		while(itr != head)
		{
		    s += ","+itr.val;
		    itr = itr.next;
		}
		s+="]>";
		return s;
	}

	public static class DNode {
		public DNode prev;
		public DNode next;
		public double val;
		
		private DNode(double val) {
			this(null, val, null);
		}
		
		private DNode(DNode prev, double val, DNode next) {
			this.prev = prev;
			this.val = val;
			this.next =next;
		}
	}
	
}
