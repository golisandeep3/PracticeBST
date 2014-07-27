import java.io.*;

class Node
{
	int data;
	Node left;
	Node right;
}
class BinaryTree
{
	public Node root=null;
	public Node head=null;


	public boolean lookup(int value)
	{
		return search(value,root);
	}
	private boolean search(int value,Node root)
	{
		if(root==null)
		{
			return false;
		}
		if(root.data == value)
			return true;
		else if(root.data < value)
			return search(value,root.right);
		else
			return search(value,root.left);
	}
	public void inorder()
	{
		inorderTraversal(root);
	}
	public void inorderTraversal(Node temp)
	{
		if(temp==null)
			return;
		inorderTraversal(temp.left);
		System.out.println(temp.data);
		inorderTraversal(temp.right);
	}
	public void insert_dummy(int value)
	{
		Node temp=root.right;
		Node n=new Node();
		n.left=null;
		n.right=null;
		n.data=value;
		while(temp.left!=null)
		{
			temp=temp.left;
			
		}
		temp.left = n;
	}
	public  void insert(int value)
	{
		if(root==null)
		{
			root = new Node();
			root.data = value;
			root.left=null;
			root.right=null;
		}
		else
		{
			Node temp =root;
			Node parent = temp;
			
			while(temp!=null)
			{
				parent = temp;
				if(temp.data <= value)
				{
					temp = temp.right;
				}
				else
				{
					temp  =temp.left;
				}
				
			}
			Node newNode = new Node();
			newNode.data = value;
			newNode.left = null;
			newNode.right = null;
			if(parent.data <= value)
				parent.right = newNode;
			else
				parent.left  =newNode;
		}
		
	}
	
	public int size()
	{
		return sizeofTree(root);
	}
	private int sizeofTree(Node temp)
	{
		if(temp==null)
			return 0;
		return sizeofTree(temp.left)+sizeofTree(temp.right)+1;
	}
	public int maxDepth()
	{
		return maxdepth(root);
	}
	private int maxdepth(Node temp)
	{
		if(temp==null)
			return 0;
		return Math.max(1+maxdepth(temp.left), 1+maxdepth(temp.right));
	}
	public int minValue()
	{
		return minvalue(root);
	}
	public int minvalue(Node temp)
	{
		if(temp==null)
			return -1;
		while(temp.left!=null)
		{
			temp = temp.left;
		}
		return temp.data;
	}
	public boolean hasPathSum(int sum)
	{
		return haspathsum(root,sum);
	}
	public boolean haspathsum(Node temp,int sum)
	{
		if(sum <0)
			return false;
		if(temp==null)
		{
			if(sum==0)
				return true;
			else
				return false;
		}
		return haspathsum(temp.left,sum-temp.data)||haspathsum(temp.right,sum-temp.data);
	}
	public void printPaths()
	{
		printpaths(root,"");
	}
	private void printpaths(Node temp,String path)
	{
		if(temp.left==null && temp.right==null)
		{
			System.out.println(path+ " "+temp.data);
			return;
		}
		
		path+=temp.data+" ";
		if(temp.left!=null)
		printpaths(temp.left,path);
		if(temp.right!=null)
		printpaths(temp.right,path);
		
	}
	public void mirror()
	{
		mirrortree(root);
	}
	public void mirrortree(Node temp)
	{
		if(temp==null)
			return;
		
		if(temp.left!=null||temp.right!=null)
		{
			Node temp1 = temp.left;
			temp.left = temp.right;
			temp.right = temp1;
		}
		
		mirrortree(temp.left);
		mirrortree(temp.right);
	
	}
	public void doubleTree()
	{
		doubletree(root);
	}
	public void doubletree(Node temp)
	{
		if(temp==null)
			return;
		doubletree(temp.left);
		doubletree(temp.right);
		Node newNode = new Node();
		newNode.data = temp.data;
		newNode.right=null;
		newNode.left = temp.left;
		temp.left = newNode;
	}
	public boolean sameTree(BinaryTree obj)
	{
		return compare(root,obj.root);
	}
	public boolean compare(Node temp1,Node temp2)
	{
		if((temp1==null && temp2!=null)||(temp1!=null  && temp2==null))
			return false;
		else if(temp1==null && temp2==null)
			return true;
		return compare(temp1.left,temp2.left) && compare(temp1.right,temp2.right)&& (temp1.data==temp2.data);
	}
	public int num_binarytrees(int n)
	{
		return num_tree(1,n);
	}
	private int num_tree(int n1,int n2)
	{
		if(n1>n2)
			return 1;
		if(n1==n2)
			return 1;
		int sum=0;
		for(int i=n1;i<=n2;i++)
		sum +=num_tree(n1,i-1)*num_tree(i+1,n2);
		
		return sum;
	}
	public boolean check_bst(BinaryTree t)
	{
		return checkbst(t.root,Integer.MAX_VALUE,Integer.MIN_VALUE);
	}
	private boolean checkbst(Node temp,int Maxdata,int mindata)
	{
		System.out.println("mindata: "+mindata);
		if(temp.left==null && temp.right==null)
			return true;
		boolean tleft=true;
		boolean tright=true;
		if(temp.left!=null)
		{
			if(temp.data >=temp.left.data && temp.left.data <= Maxdata && temp.left.data >mindata)
				tleft=true;
			else
				tleft=false;
			
			tleft = tleft&& checkbst(temp.left,temp.data,mindata);
		}
		else
			tleft=true;
		if(temp.right!=null)
		{
			System.out.println("data: "+temp.right.data);
			if(temp.data <temp.right.data && temp.right.data > mindata && temp.right.data <=Maxdata)
				tright=true;
			else
				tright=false;
			tright = tright && checkbst(temp.right,Maxdata,temp.data);
		}
		else
			tright=true;
		
		return tleft&&tright;
	}
