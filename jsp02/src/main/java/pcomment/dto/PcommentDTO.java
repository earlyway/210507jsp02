package pcomment.dto;

public class PcommentDTO {
	private String pcode;
	private String pwriter;
	private String pcontent;
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPwriter() {
		return pwriter;
	}
	public void setPwriter(String pwriter) {
		this.pwriter = pwriter;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	@Override
	public String toString() {
		return "PcommentDTO [pcode=" + pcode + ", pwriter=" + pwriter + ", pcontent=" + pcontent + "]";
	}
	
	

}
