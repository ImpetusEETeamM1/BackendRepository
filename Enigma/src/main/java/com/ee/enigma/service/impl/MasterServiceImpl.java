package com.ee.enigma.service.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ee.enigma.common.CommonUtils;
import com.ee.enigma.common.Constants;
import com.ee.enigma.common.EnigmaException;
import com.ee.enigma.dao.MasterDao;
import com.ee.enigma.model.Master;
import com.ee.enigma.request.Request;
import com.ee.enigma.response.EnigmaResponse;
import com.ee.enigma.response.ResponseCode;
import com.ee.enigma.service.MasterService;

@Service(value = "masterService")
@Transactional
public class MasterServiceImpl implements MasterService {

    private static final Logger LOGGER = Logger.getLogger(MasterServiceImpl.class);
    private MasterDao masterDao;

    @Autowired
    @Qualifier(value = "masterDao")
    public void setMasterDao(MasterDao masterDao) {
        this.masterDao = masterDao;
    }

    @Override
    public EnigmaResponse updateMasterPassword(Request masterInfo) throws EnigmaException {
        try {
            String currentMasterPassword = null;
            String newMasterPassword = null;
            try {
                currentMasterPassword = masterInfo.getParameters().getCurrentMasterPassword();
                newMasterPassword = masterInfo.getParameters().getNewMasterPassword();
            } catch (Exception e) {
                LOGGER.error(e);
                return CommonUtils.badRequest();
            }
            if (null == currentMasterPassword || null == newMasterPassword || currentMasterPassword.isEmpty()
                    || newMasterPassword.isEmpty()) {
                return CommonUtils.badRequest();
            }

            Master masterData = masterDao.getMasterInfo();
            if (currentMasterPassword.equals(masterData.getMasterPassword())) {
                masterData.setMasterPassword(newMasterPassword);
                masterDao.updateMasterInfo(masterData);
                return successResponse();
            } else {
                return wrongPassword();
            }
        } catch (HibernateException e) {
            throw new EnigmaException("Excepton in " + new Object() {
            }.getClass().getEnclosingMethod().getName() + "()  : " + e);
        } catch (Exception e) {
            throw new EnigmaException("Excepton in " + new Object() {
            }.getClass().getEnclosingMethod().getName() + "()  : " + e, e);
        }

    }

    private EnigmaResponse wrongPassword() {
        ResponseCode responseCode = new ResponseCode();
        EnigmaResponse response = new EnigmaResponse();
        responseCode.setCode(Constants.CODE_AUTHENTICATION_FAILD);
        responseCode.setMessage(Constants.MESSAGE_WRONG_PASSWORD);
        response.setResponseCode(responseCode);
        return response;
    }

    private EnigmaResponse successResponse() {
        ResponseCode responseCode = new ResponseCode();
        EnigmaResponse response = new EnigmaResponse();
        responseCode.setCode(Constants.CODE_SUCCESS);
        responseCode.setMessage(Constants.MESSAGE_SUCCESSFULLY_UPDATED);
        response.setResponseCode(responseCode);
        return response;
    }

}
