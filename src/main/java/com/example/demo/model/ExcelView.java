package com.example.demo.model;

import java.util.Map;
import java.util.Objects;

public class ExcelView {

    private String idTicketActionHistory;
    private String idTicket;
    private String idUtente;
    private String actionRecipientIdUtente;
    private String ticketAction;

    private Integer idExcelRowInizio;
    private Integer idExcelRowFine;

    private Long tempoTrascorso;


    private boolean skip;


    private Map<String, Object> mapDetails;

    public ExcelView() {
    }

    public ExcelView(ExcelView v){
        this.idTicketActionHistory = v.getIdTicketActionHistory();
        this.idTicket = v.getIdTicket();
        this.idUtente = v.getIdUtente();
        this.actionRecipientIdUtente = v.getActionRecipientIdUtente();
        this.ticketAction = v.getTicketAction();
        this.mapDetails = v.getMapDetails();
    }

//    public ExcelView(String idTicket, Integer idExcelRow) {
//        this.idTicket = idTicket;
//        this.idExcelRow = idExcelRow;
//    }

    public ExcelView(String idTicketActionHistory, String idTicket, String idUtente, String actionRecipientIdUtente, String ticketAction, Map<String, Object> mapDetails) {
        this.idTicketActionHistory = idTicketActionHistory;
        this.idTicket = idTicket;
        this.idUtente = idUtente;
        this.actionRecipientIdUtente = actionRecipientIdUtente;
        this.ticketAction = ticketAction;
        this.mapDetails = mapDetails;
    }

    public ExcelView(String idTicket, Map<String, Object> mapDetails) {
        this.idTicket = idTicket;
        this.mapDetails = mapDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExcelView)) return false;
        ExcelView excelView = (ExcelView) o;
        return Objects.equals(getIdExcelRowInizio(), excelView.getIdExcelRowInizio()) &&
                Objects.equals(getIdTicket(), excelView.getIdTicket()) &&
                        Objects.equals(getIdExcelRowFine(), excelView.getIdExcelRowFine()
                );
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdTicket(), getIdExcelRowInizio());
    }

    @Override
    public String toString() {
        return "ExcelView{" +
                "idTicketActionHistory='" + idTicketActionHistory + '\'' +
                ", idTicket='" + idTicket + '\'' +
                ", idUtente='" + idUtente + '\'' +
                ", ActionRecipientIdUtente='" + actionRecipientIdUtente + '\'' +
                ", TicketAction='" + ticketAction + '\'' +
                ", idExcelRowInizio=" + idExcelRowInizio +
                ", idExcelRowFine=" + idExcelRowFine +
                ", tempoTrascorso=" + tempoTrascorso +
                ", mapDetails=" + mapDetails +
                '}';
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public String getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(String idUtente) {
        this.idUtente = idUtente;
    }

    public String getActionRecipientIdUtente() {
        return actionRecipientIdUtente;
    }

    public void setActionRecipientIdUtente(String actionRecipientIdUtente) {
        actionRecipientIdUtente = actionRecipientIdUtente;
    }

    public String getTicketAction() {
        return ticketAction;
    }

    public void setTicketAction(String ticketAction) {
        ticketAction = ticketAction;
    }

    public Map<String, Object> getMapDetails() {
        return mapDetails;
    }

    public void setMapDetails(Map<String, Object> mapDetails) {
        this.mapDetails = mapDetails;
    }

    public Integer getIdExcelRowInizio() {
        return idExcelRowInizio;
    }

    public void setIdExcelRowInizio(Integer idExcelRowInizio) {
        this.idExcelRowInizio = idExcelRowInizio;
    }

    public Integer getIdExcelRowFine() {
        return idExcelRowFine;
    }

    public void setIdExcelRowFine(Integer idExcelRowFine) {
        this.idExcelRowFine = idExcelRowFine;
    }

    public Long getTempoTrascorso() {
        return tempoTrascorso;
    }

    public void setTempoTrascorso(Long tempoTrascorso) {
        this.tempoTrascorso = tempoTrascorso;
    }

    public String getIdTicketActionHistory() {
        return idTicketActionHistory;
    }

    public void setIdTicketActionHistory(String idTicketActionHistory) {
        this.idTicketActionHistory = idTicketActionHistory;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }
}
