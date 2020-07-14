package com.example.demo.model;

public enum ExcelEnum {
    // nome fogli
    TICKETS("tickets"),
    SHEET_1ST_ACC("sheetName1stAcceptance"),
    BLOCKING_REQUEST_EU("sheetNameBlocReqEndUser"),
    BLOCKING_REQUEST_OT("sheetNameBlocReqOperTeam"),
    INGAGGIO_TEAM("sheetNameIngaggioTeam"),

    // testate fogli derivati
    IDTICKET("IdTicket"),
    TAG("Tag"),
    IDUTENTE("IdUtente"),
    WORKLOGDATE("workLogDate"),
    WORKLOGTIME("workLogTime"),
    NOTE("note"),
    WINUSER("winUser"),
    FULL_NAME("fullName"),
    UTENTE_APER("userAper"),
    UTENTE_CHIUS("userChius"),
    NOME_APER("nomeAper"),
    NOME_CHIUS("nomeChius"),
    TEAM_APER("teamAper"),
    TEAM_CHIUS("teamChius"),
    DATE_TIME_APER("dateTimeAper"),
    DATE_TIME_CHIUS("dateTimeChius"),
    TIMEZONE_APER("timeZoneAper"),
    TIMEZONE_CHIUS("timeZoneChius"),
    TEAM("team"),
    DATA_E_ORA("dataOra"),
    TIMEZONE("timeZone"),
    TEMPO_CATEG_1ST_ACC("tempoCateg1stAcceptance"),
    TEMPO_RICHIESTO_RQ("tempoRichBlocRisp"),
    TEMPO_RICHIESTO_ENG_TEAM("tempoRichIngagTeam"),

    // actions
    CREATION("CREATION"),
    FIRST_ACCEPTANCE("1ST ACCEPTANCE"),
    CHANGE_CATEGORY("CHANGE_CATEGORY"),
    ACCEPT_BLOCKING_RQ("ACCEPT_BLOCKING_RQ"),
    REJECT_BLOCKING_RQ("REJECT_BLOCKING_RQ"),
    BLOCKING_REQUEST("BLOCKING_REQUEST"),
    ENGAGE_TEAM("ENGAGE_TEAM"),
    ACCEPT_ENGAGE("ACCEPT_ENGAGE"),
    REJECT_ENGAGE("REJECT_ENGAGE"),
    DELETE_ENGAGE("DELETE_ENGAGE"),
    CLOSE("CLOSE");





    private String code;

    ExcelEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
