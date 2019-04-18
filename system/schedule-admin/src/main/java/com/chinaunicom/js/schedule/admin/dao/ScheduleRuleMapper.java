package com.chinaunicom.js.schedule.admin.dao;

import com.chinaunicom.js.schedule.admin.entity.param.QuartzConfigQueryParam;
import com.chinaunicom.js.schedule.admin.entity.po.QuartzConfig;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ScheduleRuleMapper {

    String PUBLIC_COLUMN = "id,quartz_group,quartz_name,cron,quartz_status,quartz_class,start_time,end_time,created_time,updated_time,created_by,updated_by";

    @Options(useGeneratedKeys = true)
    @Insert("insert into quartz_config(quartz_group,quartz_name,cron,quartz_status,quartz_class,start_time,end_time,created_time,created_by)" +
            " values(#{quartzGroup},#{quartzName},#{cron},#{quartzStatus},#{quartzClass},#{startTime},#{endTime},now(),#{createdBy})")
    long insert(QuartzConfig quartzConfig);

    @Update("delete from quartz_config where id=#{id}")
    void delete(long id);

    @Update("update quartz_config set quartz_group=#{quartzGroup},quartz_name=#{quartzName},cron=#{cron},quartz_status=#{quartzStatus},quartz_class=#{quartzClass},start_time=#{startTime},end_time=#{endTime},updated_by=#{updatedBy},updated_time=now()" +
            " where id=#{id}")
    void update(QuartzConfig quartzConfig);

    @Select("select " + PUBLIC_COLUMN + " from quartz_config where id=#{id}")
    @Results(id = "quartzConfig", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "quartz_group", property = "quartzGroup", jdbcType = JdbcType.VARCHAR),
            @Result(column = "quartz_name", property = "quartzName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cron", property = "cron", jdbcType = JdbcType.VARCHAR),
            @Result(column = "quartz_status", property = "quartzStatus", jdbcType = JdbcType.INTEGER),
            @Result(column = "msg", property = "msg", jdbcType = JdbcType.VARCHAR),
            @Result(column = "quartz_class", property = "quartzClass", jdbcType = JdbcType.VARCHAR),
            @Result(column = "start_time", property = "startTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "end_time", property = "endTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "created_by", property = "createdBy", jdbcType = JdbcType.VARCHAR),
            @Result(column = "updated_by", property = "updatedBy", jdbcType = JdbcType.VARCHAR),
            @Result(column = "created_time", property = "createdTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "updated_time", property = "updatedTime", jdbcType = JdbcType.TIMESTAMP)
    })
    QuartzConfig select(long id);

    @Select("<script>" +
            "select " + PUBLIC_COLUMN +
            " from quartz_config" +
            " where 1=1" +
            "<if test='quartzName!=null and quartzName!=\"\"'>" +
            " and quartz_name like #{quartzName}" +
            "</if>" +
            "</script>")
    @ResultMap(value = "quartzConfig")
    List<QuartzConfig> query(QuartzConfigQueryParam quartzConfigQueryParam);
}