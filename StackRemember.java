
public class StackRemember {
	public int left = 0;
	public int right = 0;
    public int leftMarker = 0;	
    public int rightMarker = 0;	
    
	
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}
	public void updateLeftMarker(int marker){
		this.leftMarker = marker;
	}
	public void updateRightMarker(int marker){
		this.rightMarker = marker;
	}
	
}
