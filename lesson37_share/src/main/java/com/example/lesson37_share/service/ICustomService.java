package com.ssm.service;

import java.util.Map;

import com.ssm.entity.Custom;

public interface ICustomService {

	Map<String, Object> queryCustoms(int page, int rows);

	Map<String, Object> addCustom(Custom custom);

	Map<String, Object> delCustom(int id);

	Custom queryCustom(int id);

	Map<String, Object> updateCustom(Custom custom);

}
