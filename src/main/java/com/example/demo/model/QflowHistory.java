//package com.example.demo.model;
//
//import flexjson.JSONSerializer;
//import it.quix.framework.core.codegen.annotation.QgLabel;
//import it.quix.framework.core.codegen.annotation.QgListColumnField;
//import it.quix.framework.core.converter.annotation.QcDateTimeType;
//import it.quix.framework.core.model.AbstractModel;
//import it.quix.framework.core.model.SavedEntity;
//import it.quix.framework.core.model.annotation.QmNotClonable;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import java.util.Date;
//import java.util.Map;
//
//public class QflowHistory extends AbstractModel {
//
//    /**
//     *
//     */
//    private static final long serialVersionUID = 8914026880091176097L;
//
//    /**
//     * Logger object
//     */
//    private static Log log = LogFactory.getLog(SavedEntity.class);
//
//    @QmNotClonable
//    @QgListColumnField
//    @QgLabel(label = "id")
//    private String id;
//
//    @QgListColumnField
//    @QgLabel(label = "lottoId")
//    private String lottoId;
//
//    @QgListColumnField
//    @QgLabel(label = "processDefinitionId")
//    private String processDefinitionId;
//
//    @QgListColumnField
//    @QgLabel(label = "processInstanceId")
//    private String processInstanceId;
//
//    @QgListColumnField
//    @QgLabel(label = "taskId")
//    private String taskId;
//
//    @QgLabel(label = "dateAndTimeEvent")
//    @QcDateTimeType
//    @QgListColumnField
//    private Date dateAndTimeEvent;
//
//    @QgListColumnField
//    @QgLabel(label = "currentUser")
//    private String currentUser;
//
//    @QgListColumnField
//    @QgLabel(label = "operation")
//    private String operation;
//
//    private int notified;
//
//    @QgListColumnField
//    @QgLabel(label = "variables")
//    private String variables;
//
//    @QgListColumnField
//    @QgLabel(label = "description")
//    private String description;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getLottoId() {
//        return lottoId;
//    }
//
//    public void setLottoId(String lottoId) {
//        this.lottoId = lottoId;
//    }
//
//    public String getProcessDefinitionId() {
//        return processDefinitionId;
//    }
//
//    public void setProcessDefinitionId(String processDefinitionId) {
//        this.processDefinitionId = processDefinitionId;
//    }
//
//    public String getProcessInstanceId() {
//        return processInstanceId;
//    }
//
//    public void setProcessInstanceId(String processInstanceId) {
//        this.processInstanceId = processInstanceId;
//    }
//
//    public String getTaskId() {
//        return taskId;
//    }
//
//    public void setTaskId(String taskId) {
//        this.taskId = taskId;
//    }
//
//    public Date getDateAndTimeEvent() {
//        return dateAndTimeEvent;
//    }
//
//    public void setDateAndTimeEvent(Date dateAndTimeEvent) {
//        this.dateAndTimeEvent = dateAndTimeEvent;
//    }
//
//    public String getCurrentUser() {
//        return currentUser;
//    }
//
//    public void setCurrentUser(String currentUser) {
//        this.currentUser = currentUser;
//    }
//
//    public String getOperation() {
//        return operation;
//    }
//
//    public void setOperation(String operation) {
//        this.operation = operation;
//    }
//
//    public int getNotified() {
//        return notified;
//    }
//
//    public void setNotified(int notified) {
//        this.notified = notified;
//    }
//
//    public String getVariables() {
//        return variables;
//    }
//
//    public void setVariables(String variables) {
//        this.variables = variables;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public QflowHistory() {
//    }
//
//    public QflowHistory(String processDefinitionId, String operation, String currentUser) {
//        this.processDefinitionId = processDefinitionId;
//        this.operation = operation;
//        this.dateAndTimeEvent = new Date();
//        this.currentUser = currentUser;
//    }
//
//    public QflowHistory(String processDefinitionId, String processInstanceId, String operation, String currentUser, Map<String, Object> variables) {
//        this.processDefinitionId = processDefinitionId;
//        this.processInstanceId = processInstanceId;
//        this.operation = operation;
//        this.dateAndTimeEvent = new Date();
//        this.currentUser = currentUser;
//        if (variables != null) {
//            this.variables = new JSONSerializer().serialize(variables);
//        }
//
//    }
//
//    public QflowHistory(String processDefinitionId, String processInstanceId, String taskId, String operation, String currentUser, Map<String, Object> variables) {
//        this.processDefinitionId = processDefinitionId;
//        this.processInstanceId = processInstanceId;
//        this.taskId = taskId;
//        this.operation = operation;
//        this.dateAndTimeEvent = new Date();
//        this.currentUser = currentUser;
//        if (variables != null) {
//            this.variables = new JSONSerializer().serialize(variables);
//        }
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//
//        QflowHistory that = (QflowHistory) o;
//
//        if (!id.equals(that.id))
//            return false;
//        if (lottoId != null ? !lottoId.equals(that.lottoId) : that.lottoId != null)
//            return false;
//        if (!processDefinitionId.equals(that.processDefinitionId))
//            return false;
//        if (processInstanceId != null ? !processInstanceId.equals(that.processInstanceId) : that.processInstanceId != null)
//            return false;
//        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null)
//            return false;
//        if (dateAndTimeEvent != null ? !dateAndTimeEvent.equals(that.dateAndTimeEvent) : that.dateAndTimeEvent != null)
//            return false;
//        if (currentUser != null ? !currentUser.equals(that.currentUser) : that.currentUser != null)
//            return false;
//        if (variables != null ? !variables.equals(that.variables) : that.variables == null)
//            return false;
//        if (description != null ? !description.equals(that.description) : that.description == null)
//            return false;
//        return operation != null ? operation.equals(that.operation) : that.operation == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id.hashCode();
//        result = 31 * result + (lottoId != null ? lottoId.hashCode() : 0);
//        result = 31 * result + processDefinitionId.hashCode();
//        result = 31 * result + (processInstanceId != null ? processInstanceId.hashCode() : 0);
//        result = 31 * result + (taskId != null ? taskId.hashCode() : 0);
//        result = 31 * result + (dateAndTimeEvent != null ? dateAndTimeEvent.hashCode() : 0);
//        result = 31 * result + (currentUser != null ? currentUser.hashCode() : 0);
//        result = 31 * result + (operation != null ? operation.hashCode() : 0);
//        result = 31 * result + (variables != null ? variables.hashCode() : 0);
//        result = 31 * result + (description != null ? description.hashCode() : 0);
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return "QflowHistory{" + "id='" + id + '\'' + ", lottoId='" + lottoId + '\'' + ", processDefinitionId='" + processDefinitionId + '\''
//            + ", processInstanceId='" + processInstanceId + '\'' + ", taskId='" + taskId + '\'' + ", dateAndTimeEvent=" + dateAndTimeEvent + ", currentUser='"
//            + currentUser + '\'' + ", operation='" + operation + '\'' + ", notified=" + notified + ", variables='" + variables + '\'' + ", description='"
//            + description + '\'' + '}';
//    }
//}
