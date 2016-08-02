package com.ee.enigma.dao;

import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ee.enigma.model.DevicePushNotification;

@Repository(value = "devicePushNotificationDao")
@Transactional
public class DevicePushNotificationDaoImpl implements DevicePushNotificationDao {

	private Logger logger = Logger.getLogger(DeviceInfoDaoImpl.class);
	private SessionFactory sessionFactory;

	@Autowired
	@Qualifier(value = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public DevicePushNotification getDeviceInfo(String deviceId) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			DevicePushNotification devicePushNotification = (DevicePushNotification) session
					.load(DevicePushNotification.class, deviceId);
			logger.info(devicePushNotification.toString());
			return devicePushNotification;
		} catch (ObjectNotFoundException e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public void updateDevicePushNotification(DevicePushNotification devicePushNotification) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(devicePushNotification);
	}

	@Override
	public void saveDevicePushNotification(DevicePushNotification devicePushNotification) {
		logger.info(devicePushNotification);
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(devicePushNotification);

	}

}
