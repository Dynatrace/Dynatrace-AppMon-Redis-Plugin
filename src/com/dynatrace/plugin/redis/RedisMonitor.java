
 /**
  * This template file was generated by Dynatrace client.
  * The Dynatrace community portal can be found here: http://community.dynatrace.com/
  * For information how to publish a plugin please visit https://community.dynatrace.com/community/display/DL/How+to+add+a+new+plugin/
  **/ 

package com.dynatrace.plugin.redis;

import com.dynatrace.diagnostics.pdk.*;

import java.util.Collection;
import java.util.logging.Logger;

public class RedisMonitor implements Monitor {

	private static final Logger log = Logger.getLogger(Monitor.class.getName());

	/**
	 * Initializes the Plugin. This method is called in the following cases:
	 * <ul>
	 * <li>before <tt>execute</tt> is called the first time for this
	 * scheduled Plugin</li>
	 * <li>before the next <tt>execute</tt> if <tt>teardown</tt> was called
	 * after the last execution</li>
	 * </ul>
	 * <p>
	 * If the returned status is <tt>null</tt> or the status code is a
	 * non-success code then {@link Plugin#teardown() teardown()} will be called
	 * next.
	 * <p>
	 * Resources like sockets or files can be opened in this method.
	 * @param env
	 *            the configured <tt>MonitorEnvironment</tt> for this Plugin;
	 *            contains subscribed measures, but <b>measurements will be
	 *            discarded</b>
	 * @see Plugin#teardown()
	 * @return a <tt>Status</tt> object that describes the result of the
	 *         method call
	 */
	@Override
	public Status setup(MonitorEnvironment env) throws Exception {
		// TODO
		return new Status(Status.StatusCode.Success);
	}

	/**
	 * Executes the Monitor Plugin to retrieve subscribed measures and store
	 * measurements.
	 *
	 * <p>
	 * This method is called at the scheduled intervals. If the Plugin execution
	 * takes longer than the schedule interval, subsequent calls to
	 * {@link #execute(MonitorEnvironment)} will be skipped until this method
	 * returns. After the execution duration exceeds the schedule timeout,
	 * {@link TaskEnvironment#isStopped()} will return <tt>true</tt>. In this
	 * case execution should be stopped as soon as possible. If the Plugin
	 * ignores {@link TaskEnvironment#isStopped()} or fails to stop execution in
	 * a reasonable timeframe, the execution thread will be stopped ungracefully
	 * which might lead to resource leaks!
	 *
	 * @param env
	 *            a <tt>MonitorEnvironment</tt> object that contains the
	 *            Plugin configuration and subscribed measures. These
	*            <tt>MonitorMeasure</tt>s can be used to store measurements.
	 * @return a <tt>Status</tt> object that describes the result of the
	 *         method call
	 */
	@Override
	public Status execute(MonitorEnvironment env) throws Exception {
		log.info("execute begin");
		
		log.info("host : " + env.getHost().getAddress());
		log.info("port : " + env.getConfigLong("port"));
		log.info("auth : " + env.getConfigString("auth") );
		
		
		RedisDataProvider redisDataProvider = new RedisDataProvider(env.getHost().getAddress(),env.getConfigLong("port").intValue(), env.getConfigString("auth"));
		
		    	setMeasure(env,redisDataProvider.getRedisInfo().getMemory().getUsed_memory(),"memory","used_memory");
		    	
    			setMeasure(env,redisDataProvider.getRedisInfo().getMemory().getUsed_memory_peak(),"memory","used_memory_peak");
    			setMeasure(env,redisDataProvider.getRedisInfo().getClients().getConnected_clients(),"clients","connected_clients");
    			setMeasure(env,redisDataProvider.getRedisInfo().getStats().getInstantaneous_ops_per_sec(),"stats","instantaneous_ops_per_sec");
    			setMeasure(env,redisDataProvider.getRedisInfo().getReplication().getMaster_last_io_seconds_ago(),"replication","master_last_io_seconds_ago");
    			setMeasure(env,redisDataProvider.getRedisInfo().getReplication().getMaster_sync_in_progress(),"replication","master_sync_in_progress");
    			setMeasure(env,redisDataProvider.getRedisInfo().getReplication().getConnected_slaves(),"replication","connected_slaves");
    			setMeasure(env,redisDataProvider.getRedisInfo().getKeyspace().getKeys(),"keyspace","keys");
    			setMeasure(env,redisDataProvider.getRedisInfo().getPersistence().getRdb_last_bgsave_time_sec(),"persistence","rdb_last_bgsave_time_sec");
    			setMeasure(env,redisDataProvider.getRedisInfo().getPersistence().getRdb_bgsave_in_progress(),"persistence","rdb_bgsave_in_progress");
    			setMeasure(env,redisDataProvider.getRedisInfo().getPersistence().getRdb_changes_since_last_save(),"persistence","rdb_changes_since_last_save");
    		
    	log.info("execute end");
		return new Status(Status.StatusCode.Success);
	}
	
	
	/**
	 * Shuts the Plugin down and frees resources. This method is called in the
	 * following cases:
	 * <ul>
	 * <li>the <tt>setup</tt> method failed</li>
	 * <li>the Plugin configuration has changed</li>
	 * <li>the execution duration of the Plugin exceeded the schedule timeout</li>
	 * <li>the schedule associated with this Plugin was removed</li>
	 * </ul>
	 *
	 * <p>
	 * The Plugin methods <tt>setup</tt>, <tt>execute</tt> and
	 * <tt>teardown</tt> are called on different threads, but they are called
	 * sequentially. This means that the execution of these methods does not
	 * overlap, they are executed one after the other.
	 *
	 * <p>
	 * Examples:
	 * <ul>
	 * <li><tt>setup</tt> (failed) -&gt; <tt>teardown</tt></li>
	 * <li><tt>execute</tt> starts, configuration changes, <tt>execute</tt>
	 * ends -&gt; <tt>teardown</tt><br>
	 * on next schedule interval: <tt>setup</tt> -&gt; <tt>execute</tt> ...</li>
	 * <li><tt>execute</tt> starts, execution duration timeout,
	 * <tt>execute</tt> stops -&gt; <tt>teardown</tt></li>
	 * <li><tt>execute</tt> starts, <tt>execute</tt> ends, schedule is
	 * removed -&gt; <tt>teardown</tt></li>
	 * </ul>
	 * Failed means that either an unhandled exception is thrown or the status
	 * returned by the method contains a non-success code.
	 *
	 *
	 * <p>
	 * All by the Plugin allocated resources should be freed in this method.
	 * Examples are opened sockets or files.
	 *
	 * @see Monitor#setup(MonitorEnvironment)
	 */	@Override
	public void teardown(MonitorEnvironment env) throws Exception {
		// TODO
	}
	 
	 /*
	 private void setMeasure(MonitorEnvironment env, String row, String metricGroup, String metric)
	 {
		 String key = row.split(":")[0];
			if(key.equals(metric))
			{
				double value =  Double.parseDouble(row.split(":")[1]) ;
				setMeasure(env,value,metricGroup,metric);
			}
	 
	 }*/
	 
	 private void setMeasure(MonitorEnvironment env, Double value, String metricGroup, String metric) {
		 
		  	log.info("setMeasure : " + metricGroup + " - "+ metric +" - "  + value);
		 
			if (value != null) {
				Collection<MonitorMeasure> monitorMeasures = env.getMonitorMeasures(metricGroup, metric);
				for (MonitorMeasure subscribedMonitorMeasure : monitorMeasures) {
					if (subscribedMonitorMeasure != null) {
						subscribedMonitorMeasure.setValue(value);
					}
				}
			}
		}
	 

}