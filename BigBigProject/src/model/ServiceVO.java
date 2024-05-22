package model;

public class ServiceVO {
	private int service_no;
	private String ct_id;
	private String title;
	private String contents;
	
	public ServiceVO() {
		super();
	}

	public ServiceVO(int service_no, String ct_id, String title, String contents) {
		super();
		this.service_no = service_no;
		this.ct_id = ct_id;
		this.title = title;
		this.contents = contents;
	}

	public int getService_no() {
		return service_no;
	}

	public void setService_no(int service_no) {
		this.service_no = service_no;
	}

	public String getCt_id() {
		return ct_id;
	}

	public void setCt_id(String ct_id) {
		this.ct_id = ct_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void printService() {
		System.out.printf("%3s ", service_no);
		System.out.printf("%-10s ",title);
		System.out.printf("%-25s ",contents);
		System.out.println();
	}
	
	
	
}
