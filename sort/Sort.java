package com.zhu.sort;

public class Sort {
	/**
	 * Ö±½Ó²åÈëÅÅĞò
	 * @param array
	 */
	public static void insertSort(int [] array){
		if(array==null)
			 return ;
		for(int i=1;i<array.length;i++){
			int temp=array[i];
			int j=i-1;
			while(j>=0&&array[j]>temp){
				array[j+1]=array[j];
				--j;
			}
			array[j+1]=temp;
		}
	}
	/**
	 * Ï£¶ûÅÅĞò
	 * @param array
	 */
	public static void shellSort(int [] array){
		if(array==null)
			return ;
		int len=array.length/2;
		while(len>=1){
			for(int i=len;i<array.length;i++){
				int temp=array[i];
				int j=i-len;
				while(j>=0&&array[j]>temp){
					array[j+len]=array[j];
					j-=len;
				}
				array[j+len]=temp;
			}
			len-=1;
		}
	}
	/**
	 * Ã°ÅİÅÅĞò
	 * @param array
	 */
	public static void bubbleSort(int [] array){
		if(array==null)
			return ;
		boolean isChange=false;
		int len=array.length;
		for(int i=0;i<len;i++){	
			for(int j=len-1;j>i;j--){
				if(array[j]<array[j-1]){
					int temp=array[j];
					array[j]=array[j-1];
					array[j-1]=temp;
					isChange=true;
				}
			}
			if(!isChange)
				return ;
			isChange=false;
		}
	}
	/**
	 * ¿ìËÙÅÅĞò
	 * @param array
	 */
	public static void quickSort(int [] array){
		if(array==null)
			return ;
		int len=array.length;
		quickSort(array, 0, len-1);
		
	}
	private static void quickSort(int [] array,int start,int end){
		if(start>=end)
			return ;
		int temp=array[start];
		int l=start,r=end;
		while(l<r){
			while(array[r]>temp&&l<r)
				--r;
			array[l]=array[r];
			while(array[l]<=temp&&l<r)
				++l;
			array[r]=array[l];
		}
		array[l]=temp;
		quickSort(array,start,l-1);
		quickSort(array,l+1,end);
	}
	/**
	 * Ñ¡ÔñÅÅĞò
	 * @param array
	 */
	public static void selectSort(int [] array){
		if(array==null)
				return;
		int len=array.length;
		int minIndex;
		for(int i=0;i<len;i++){
			minIndex=i;
			for(int j=i+1;j<len;j++){
				if(array[minIndex]>array[j]){
					minIndex=j;
				}
			}
			int temp=array[i];
			array[i]=array[minIndex];
			array[minIndex]=temp;
		}
	}
	/**
	 * ¶ÑÅÅĞò ×î´ó¶Ñ
	 * @param array
	 */
	public static void heapSort(int [] array){
		if(array==null)
			return;
		int N=array.length-1;
		for(int i=N/2-1;i>=0;i--){
		    heapSort(i,array,N);	
		}
		
		while(N>0){
			swap(0, N, array);
			heapSort(0,array,--N);
			
		}
	}
	private static void heapSort(int n,int[] array,int N){
		while(n*2+1<=N){
			int k=n*2+1;
			if(k<N&&array[k]<array[k+1]){
				++k;
			}
			if(array[n]>=array[k])  
				break;
				swap(n, k, array);
			n=k;
		}
	}
	
	/**
	 * ¹é²¢ÅÅĞò
	 * @param array
	 */
	public static void mergeSort(int [] array){
		if(array==null)
			return;
		mergeSort(array, 0, array.length-1);
	}
	private static void mergeSort(int [] array,int start,int end){
		if(start>=end){
			return;
		}
		int mid=(start+end)/2;
		mergeSort(array, start, mid);
		mergeSort(array, mid+1, end);
		merge(array, start, mid, end);
	}
	private static void merge(int [] array,int start,int mid,int end){
		int [] temps=new int[end-start+1];
		int l1=start,l2=mid+1;
		int i=0;
		while(l1<=mid&&l2<=end){
			if(array[l1]>array[l2])
				temps[i++]=array[l2++];
			else
				temps[i++]=array[l1++];
		}
		while(l1<=mid)
			temps[i++]=array[l1++];
		while(l2<=end)
			temps[i++]=array[l2++];
		for(i=0;i<=end-start;i++)
			array[i+start]=temps[i];
	}
	
	private static void swap(int a,int b,int [] array){
		int temp=array[a];
		array[a]=array[b];
		array[b]=temp;
	}
	public static void main(String[] args) {
		int []array={8,2,7,5,3,9,6,1,4};
		//shellSort(array);
		//insertSort(array);
		//bubbleSort(array);
		//quickSort(array);
		//selectSort(array);
		//heapSort(array);
		mergeSort(array);
		for(int i=0;i<array.length;i++){
			System.out.println(array[i]);
		}
	}
}
