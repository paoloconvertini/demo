package dao;

import model.Persona;
import search.PersonaSearch;

import java.util.List;

public interface PersonaDAO {

    public List<Persona> getPersonaList(PersonaSearch search) throws Exception;

}
