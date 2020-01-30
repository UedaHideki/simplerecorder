package rev.simplerecorder.model;

public class MItem {
	private int id;
	private int projectId;
	private int recoredId;
	private String description;
	private String reviewerPersion;
	private String isapplied;
	private String reply;
	private String replyDate;
	private String confirm;
	private String confirmPerson;
	private String confirmDate;
	private String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getRecoredId() {
		return recoredId;
	}
	public void setRecoredId(int recoredId) {
		this.recoredId = recoredId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReviewerPersion() {
		return reviewerPersion;
	}
	public void setReviewerPersion(String reviewerPersion) {
		this.reviewerPersion = reviewerPersion;
	}
	public String getIsapplied() {
		return isapplied;
	}
	public void setIsapplied(String isapplied) {
		this.isapplied = isapplied;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getConfirmPerson() {
		return confirmPerson;
	}
	public void setConfirmPerson(String confirmPerson) {
		this.confirmPerson = confirmPerson;
	}
	public String getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
