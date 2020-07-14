//package com.example.demo.view;
//
//import it.quix.framework.core.model.Template;
//import java.util.List;
//import java.util.Objects;
//
//public class InvioAllegatoMailPropertiesView {
//
//    private String id;
//
//    private String qery;
//
//    private Template templateSubject;
//
//    private Template templateBody;
//
//    private Boolean zipYN;
//
//    private Boolean mailMassiveYN;
//
//    private List<String> toList;
//
//    private List<String> ccList;
//
//    private List<String> bccList;
//
//    private String mittente;
//
//    private String replyTo;
//
//    public InvioAllegatoMailPropertiesView() {
//    }
//
//    public InvioAllegatoMailPropertiesView(String id, String qery, Template templateSubject, Template templateBody, Boolean zipYN, Boolean mailMassiveYN, List<String> toList, List<String> ccList, List<String> bccList, String mittente, String replyTo) {
//        this.id = id;
//        this.qery = qery;
//        this.templateSubject = templateSubject;
//        this.templateBody = templateBody;
//        this.zipYN = zipYN;
//        this.mailMassiveYN = mailMassiveYN;
//        this.toList = toList;
//        this.ccList = ccList;
//        this.bccList = bccList;
//        this.mittente = mittente;
//        this.replyTo = replyTo;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof InvioAllegatoMailPropertiesView)) return false;
//        InvioAllegatoMailPropertiesView that = (InvioAllegatoMailPropertiesView) o;
//        return Objects.equals(getId(), that.getId()) &&
//                Objects.equals(getQery(), that.getQery()) &&
//                Objects.equals(getTemplateSubject(), that.getTemplateSubject()) &&
//                Objects.equals(getTemplateBody(), that.getTemplateBody()) &&
//                Objects.equals(getZipYN(), that.getZipYN()) &&
//                Objects.equals(getMailMassiveYN(), that.getMailMassiveYN()) &&
//                Objects.equals(getToList(), that.getToList()) &&
//                Objects.equals(getCcList(), that.getCcList()) &&
//                Objects.equals(getBccList(), that.getBccList()) &&
//                Objects.equals(getMittente(), that.getMittente()) &&
//                Objects.equals(getReplyTo(), that.getReplyTo());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getQery(), getTemplateSubject(), getTemplateBody(), getZipYN(), getMailMassiveYN(), getToList(), getCcList(), getBccList(), getMittente(), getReplyTo());
//    }
//
//    @Override
//    public String toString() {
//        return "InvioAllegatoMailPropertiesView{" +
//                "id='" + id + '\'' +
//                ", qery='" + qery + '\'' +
//                ", templateSubject=" + templateSubject +
//                ", templateBody=" + templateBody +
//                ", zipYN=" + zipYN +
//                ", mailMassiveYN=" + mailMassiveYN +
//                ", toList=" + toList +
//                ", ccList=" + ccList +
//                ", bccList=" + bccList +
//                ", mittente='" + mittente + '\'' +
//                ", replyTo='" + replyTo + '\'' +
//                '}';
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getQery() {
//        return qery;
//    }
//
//    public void setQery(String qery) {
//        this.qery = qery;
//    }
//
//    public Template getTemplateSubject() {
//        return templateSubject;
//    }
//
//    public void setTemplateSubject(Template templateSubject) {
//        this.templateSubject = templateSubject;
//    }
//
//    public Template getTemplateBody() {
//        return templateBody;
//    }
//
//    public void setTemplateBody(Template templateBody) {
//        this.templateBody = templateBody;
//    }
//
//    public Boolean getZipYN() {
//        return zipYN;
//    }
//
//    public void setZipYN(Boolean zipYN) {
//        this.zipYN = zipYN;
//    }
//
//    public Boolean getMailMassiveYN() {
//        return mailMassiveYN;
//    }
//
//    public void setMailMassiveYN(Boolean mailMassiveYN) {
//        this.mailMassiveYN = mailMassiveYN;
//    }
//
//    public List<String> getToList() {
//        return toList;
//    }
//
//    public void setToList(List<String> toList) {
//        this.toList = toList;
//    }
//
//    public List<String> getCcList() {
//        return ccList;
//    }
//
//    public void setCcList(List<String> ccList) {
//        this.ccList = ccList;
//    }
//
//    public List<String> getBccList() {
//        return bccList;
//    }
//
//    public void setBccList(List<String> bccList) {
//        this.bccList = bccList;
//    }
//
//    public String getMittente() {
//        return mittente;
//    }
//
//    public void setMittente(String mittente) {
//        this.mittente = mittente;
//    }
//
//    public String getReplyTo() {
//        return replyTo;
//    }
//
//    public void setReplyTo(String replyTo) {
//        this.replyTo = replyTo;
//    }
//}
