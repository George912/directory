package ru.bellintegrator.app.service;

import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.AnalyticalInfo;

/**
 * Интерфейс, сервиса получения статистической информации.
 */
public interface AnalyticalInfoService {

    /**
     * Получает из БД статистическую информацию, формирует объект,
     * содержащий данные и возвращает его.
     * @return
     * @throws ServiceException
     */
    AnalyticalInfo collectAnalyticalInfo() throws ServiceException;

}
