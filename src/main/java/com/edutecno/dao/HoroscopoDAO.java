// Interface HoroscopoDAO
package com.edutecno.dao;

import com.edutecno.modelo.Horoscopo;
import java.util.List;

public interface HoroscopoDAO {
    List<Horoscopo> obtenerHoroscopo();
    Horoscopo buscarHoroscopoPorAnimal(String animal);
}

