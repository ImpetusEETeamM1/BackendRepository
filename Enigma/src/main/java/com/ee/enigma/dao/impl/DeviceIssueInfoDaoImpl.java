package com.ee.enigma.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ee.enigma.common.CommonUtils;
import com.ee.enigma.common.Constants;
import com.ee.enigma.dao.DeviceIssueInfoDao;
import com.ee.enigma.dto.DeviceReportDto;
import com.ee.enigma.dto.TopDeviceDto;
import com.ee.enigma.model.DeviceIssueInfo;

@Repository(value = "deviceIssueInfoDao")
@Transactional
public class DeviceIssueInfoDaoImpl implements DeviceIssueInfoDao {

    private static final Logger LOGGER = Logger.getLogger(DeviceIssueInfoDaoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    @Qualifier(value = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<DeviceIssueInfo> getDeviceIssueList(String deviceId, java.util.Date beginDate, java.util.Date endDate) {
        try {
            String hql;
            Query query;
            Session session = this.sessionFactory.getCurrentSession();
            if (beginDate == null) {
                hql = "from DeviceIssueInfo where deviceId= :deviceId order by issueTime desc";
            } else {
                hql = "from DeviceIssueInfo where deviceId= :deviceId and ((issueTime between :beginDate and :endDate) or (submitTime between :beginDate and :endDate)) order by issueTime desc";
            }
            query = session.createQuery(hql);
            query.setParameter("deviceId", deviceId);
            if (beginDate != null) {
                query.setParameter("beginDate", beginDate);
                query.setParameter("endDate", endDate);
            }
            List<DeviceIssueInfo> deviceIssueInfoList = (List<DeviceIssueInfo>) query.list();
            if (null == deviceIssueInfoList || deviceIssueInfoList.isEmpty()) {
                return new ArrayList<>();
            }
            LOGGER.info(deviceIssueInfoList.toString());
            return deviceIssueInfoList;
        } catch (HibernateException e) {
            LOGGER.error(e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<DeviceIssueInfo> getDeviceIssueListByUserId(String userId, java.util.Date beginDate,
            java.util.Date endDate) {
        try {
            String hql ;
            Query query ;
            Session session = this.sessionFactory.getCurrentSession();
            if (beginDate == null) {
                hql = "from DeviceIssueInfo where deviceId= :deviceId order by issueTime desc";
            } else {
                hql = "from DeviceIssueInfo where userId= :userId and ((issueTime between :beginDate and :endDate) or (submitTime between :beginDate and :endDate)) order by issueTime desc";
            }
            query = session.createQuery(hql);
            query.setParameter("userId", userId);
            if (beginDate != null) {
                query.setParameter("beginDate", beginDate);
                query.setParameter("endDate", endDate);
            }
            List<DeviceIssueInfo> deviceIssueInfoList = (List<DeviceIssueInfo>) query.list();
            if (null == deviceIssueInfoList || deviceIssueInfoList.isEmpty()) {
                return new ArrayList<>();
            }
            LOGGER.info(deviceIssueInfoList.toString());
            return deviceIssueInfoList;
        } catch (HibernateException e) {
            LOGGER.error(e);
            return new ArrayList<>();
        }
    }

    @Override
    public void createDeviceIssueInfo(DeviceIssueInfo deviceIssueInfo) {
        LOGGER.info(deviceIssueInfo);
        Session session = this.sessionFactory.getCurrentSession();
        deviceIssueInfo.setIssueId(issueIdGenerator(deviceIssueInfo.getDeviceId()));
        session.persist(deviceIssueInfo);
    }

    @Override
    public void updateDeviceIssueInfo(DeviceIssueInfo deviceIssueInfo) {
        LOGGER.info(deviceIssueInfo);
        Session session = this.sessionFactory.getCurrentSession();
        session.update(deviceIssueInfo);
    }

    @Override
    public List<DeviceIssueInfo> getDeviceIssueInfoList(String deviceId) {
        try {
            String hql = "from DeviceIssueInfo where deviceId= :deviceId order by issueTime desc ";
            Session session = this.sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql);
            query.setParameter("deviceId", deviceId);
            List<DeviceIssueInfo> deviceIssueInfoList = (List<DeviceIssueInfo>) query.list();
            if (null == deviceIssueInfoList || deviceIssueInfoList.isEmpty()) {
                return new ArrayList<>();
            }
            LOGGER.info(deviceIssueInfoList.toString());
            return deviceIssueInfoList;
        } catch (HibernateException e) {
            LOGGER.error(e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<DeviceIssueInfo> getDeviceIssueInfoList(String deviceId, String userId) {
        try {
            String hql = "from DeviceIssueInfo where deviceId= :deviceId and userId= :userId";
            Session session = this.sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql);
            query.setParameter("deviceId", deviceId);
            query.setParameter("userId", userId);
            List<DeviceIssueInfo> deviceIssueInfoList = (List<DeviceIssueInfo>) query.list();
            if (null == deviceIssueInfoList || deviceIssueInfoList.isEmpty()) {
                return new ArrayList<>();
            }
            LOGGER.info(deviceIssueInfoList.toString());
            return deviceIssueInfoList;
        } catch (HibernateException e) {
            LOGGER.error(e);
            return new ArrayList<>();
        }
    }

    private String issueIdGenerator(String deviceId) {
        return deviceId + "_" + CommonUtils.getTime();
    }

    @Override
    public DeviceIssueInfo getDeviceIssueInfoByIssueID(String issueId) {
        Session session = this.sessionFactory.getCurrentSession();
        DeviceIssueInfo deviceIssueInfo = (DeviceIssueInfo) session.load(DeviceIssueInfo.class, issueId);
        LOGGER.info(deviceIssueInfo.toString());
        return deviceIssueInfo;
    }

    @Override
    public List<DeviceIssueInfo> getDeviceIssueList(java.util.Date beginDate, java.util.Date endDate,
            String issueType) {
        try {
            String hql;
            Query query;
            Session session = this.sessionFactory.getCurrentSession();
            if (beginDate != null) {
                if (issueType.equals(Constants.DEVICE_ISSUE)) {
                    hql = "from DeviceIssueInfo where (issueTime between :beginDate and :endDate) order by issueTime desc";
                } else if (issueType.equals(Constants.DEVICE_SUBMIT)) {
                    hql = "from DeviceIssueInfo where (submitTime between :beginDate and :endDate) order by submitTime desc";
                } else {
                    hql = "from DeviceIssueInfo order by issueTime desc";
                }
            } else {
                hql = "from DeviceIssueInfo order by issueTime desc";
            }
            query = session.createQuery(hql);
            if (beginDate != null) {
                query.setParameter("beginDate", beginDate);
                query.setParameter("endDate", endDate);
            }
            List<DeviceIssueInfo> deviceIssueInfoList = (List<DeviceIssueInfo>) query.list();
            if (null == deviceIssueInfoList || deviceIssueInfoList.isEmpty()) {
                return new ArrayList<>();
            }
            LOGGER.info(deviceIssueInfoList.toString());
            return deviceIssueInfoList;
        } catch (HibernateException e) {
            LOGGER.error(e);
            return new ArrayList<>();
        }
    }

    @Override
    public DeviceIssueInfo getDeviceIssueInfoByDeviceId(String deviceId) {
        try {
            String hql;
            Query query;
            Session session = this.sessionFactory.getCurrentSession();
            hql = "from DeviceIssueInfo where deviceId= :deviceId AND submitByAdmin=0 order by submitTime desc";
            query = session.createQuery(hql);
            query.setParameter("deviceId", deviceId);
            query.setMaxResults(1);
            List<DeviceIssueInfo> deviceIssueInfoList = (List<DeviceIssueInfo>) query.list();
            if (null == deviceIssueInfoList || deviceIssueInfoList.isEmpty()) {
                return null;
            }
            LOGGER.info(deviceIssueInfoList.toString());
            return deviceIssueInfoList.get(0);
        } catch (HibernateException e) {
            LOGGER.error(e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TopDeviceDto> getTopDevices(java.util.Date lastMonthDate) {
        try {
            String hql;
            Query query;
            Session session = this.sessionFactory.getCurrentSession();
            hql = "SELECT dif.deviceId,d.deviceName, COUNT(*) AS count FROM DeviceIssueInfo dif , DeviceInfo d "
                    + "WHERE dif.deviceId=d.deviceId AND dif.issueTime > :lastMonthDate GROUP BY dif.deviceId";
            query = session.createQuery(hql);
            query.setParameter("lastMonthDate", lastMonthDate);
            List<Objects[]> topDeviceDtoListObjects = (List<Objects[]>) query.list();
            List<TopDeviceDto> topDeviceDtoList = new ArrayList<TopDeviceDto>();
            if (null == topDeviceDtoListObjects || topDeviceDtoListObjects.isEmpty()) {
                return new ArrayList<>();
            } else {
                for (Object[] topDevice : topDeviceDtoListObjects) {
                    TopDeviceDto topDeviceDto = new TopDeviceDto();
                    topDeviceDto.setDeviceId((String) topDevice[0]);
                    topDeviceDto.setDeviceName((String) topDevice[1]);
                    topDeviceDto.setCount((Long) topDevice[2]);
                    topDeviceDtoList.add(topDeviceDto);
                }
            }
            LOGGER.info(topDeviceDtoList.toString());
            return topDeviceDtoList;
        } catch (HibernateException e) {
            LOGGER.error(e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<DeviceIssueInfo> getAllDeviceIssueInfoList() {
        try {
            String hql = "from DeviceIssueInfo order by issueTime desc ";
            Session session = this.sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql);
            List<DeviceIssueInfo> deviceIssueInfoList = (List<DeviceIssueInfo>) query.list();
            if (null == deviceIssueInfoList || deviceIssueInfoList.isEmpty()) {
                return new ArrayList<>();
            }
            LOGGER.info(deviceIssueInfoList.toString());
            return deviceIssueInfoList;
        } catch (HibernateException e) {
            LOGGER.error(e);
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DeviceReportDto> getDeviceReport(String deviceId, java.util.Date startDate, java.util.Date endDate) {
        String hql;
        Query query;
        Session session = this.sessionFactory.getCurrentSession();
        hql = "select activity.loginTime, activity.logoutTime, user.userName, user.userId  from UserActivity activity,"
                + " UserInfo user where activity.deviceId= :deviceId and activity.userId=user.userId "
                + "and activity.loginTime between :startDate and :endDate order by loginTime";
        query = session.createQuery(hql);
        query.setParameter("deviceId", deviceId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        List<Objects[]> deviceReportDtoObjects = (List<Objects[]>) query.list();
        List<DeviceReportDto> deviceReportDtoList = new ArrayList<DeviceReportDto>();
        if (null == deviceReportDtoObjects || deviceReportDtoObjects.isEmpty()) {
            return new ArrayList<>();
        } else {
            for (Object[] deviceReport : deviceReportDtoObjects) {
                DeviceReportDto deviceReportDto = new DeviceReportDto();
                deviceReportDto.setLoginTIme(CommonUtils.timestampToDate((Timestamp) deviceReport[0]));
                deviceReportDto.setLogOutTime(CommonUtils.timestampToDate((Timestamp) deviceReport[1]));
                deviceReportDto.setUserName((String) deviceReport[2]);
                deviceReportDto.setUserId((String) deviceReport[3]);
                deviceReportDtoList.add(deviceReportDto);
            }
        }
        return deviceReportDtoList;
    }

}