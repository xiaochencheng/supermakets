package cn.smbms.pojo;

import java.util.Date;

public class UploadFile {

	// id 附件唯一性
		private int fileid;
		// 用户表中的id，主外键关联
		private int userid;
		// 附件的真实名字
		private String realfilename;
		// 存到硬盘上的名字，保存的名字
		private String savefilename;
		// 文件大小
		private long filesize;
		// 上传附件的uid
		private String uploadPerson;

		// 上传时间
		private Date uploadDate;

		// 类型 1，工作证 2 头像
		private int fileType;

		public int getFileid() {
			return fileid;
		}

		public void setFileid(int fileid) {
			this.fileid = fileid;
		}

		public int getUserid() {
			return userid;
		}

		public void setUserid(int userid) {
			this.userid = userid;
		}

		public String getRealfilename() {
			return realfilename;
		}

		public void setRealfilename(String realfilename) {
			this.realfilename = realfilename;
		}

		public String getSavefilename() {
			return savefilename;
		}

		public void setSavefilename(String savefilename) {
			this.savefilename = savefilename;
		}

		public long getFilesize() {
			return filesize;
		}

		public void setFilesize(long filesize) {
			this.filesize = filesize;
		}

		public String getUploadPerson() {
			return uploadPerson;
		}

		public void setUploadPerson(String uploadPerson) {
			this.uploadPerson = uploadPerson;
		}

		public Date getUploadDate() {
			return uploadDate;
		}

		public void setUploadDate(Date uploadDate) {
			this.uploadDate = uploadDate;
		}

		public int getFileType() {
			return fileType;
		}

		public void setFileType(int fileType) {
			this.fileType = fileType;
		}

	
}
