package com.dynatrace.plugin.redis;

import java.util.logging.Logger;

import com.dynatrace.diagnostics.pdk.Monitor;

import redis.clients.jedis.Jedis;

import java.util.logging.Logger;


public class RedisDataProvider {
	
	private static final Logger log = Logger.getLogger(Monitor.class.getName());
	
	RedisInfo redisInfo;
	
	public RedisDataProvider(String host, int port, String auth){
		redisInfo=new RedisInfo();
		
		
		Jedis jedis = new Jedis(host,port);
      	if(!auth.equals("")){
    		jedis.auth(auth);
      	}

    		String[] result = jedis.info().split("\\n"); 
    		   	    		
    		double numberOfKeys = 0;
    		
    		for(String row : result)
    		{
    			// log.info(row);
    			if(row.indexOf(':')>-1){
    				
    				
    				 String key = row.split(":")[0];
    				
    					if(key.equals("used_memory"))
    					{
    						Double value = Double.parseDouble(row.split(":")[1]);
    						redisInfo.getMemory().setUsed_memory(value);
    					}
    					
    					if(key.equals("used_memory_peak"))
    					{
    						Double value = Double.parseDouble(row.split(":")[1]);
    						redisInfo.getMemory().setUsed_memory_peak(value);
    					}
    					
    					if(key.equals("connected_clients"))
    					{
    						Double value = Double.parseDouble(row.split(":")[1]);
    						redisInfo.getClients().setConnected_clients(value);
    					}
    					
    					if(key.equals("instantaneous_ops_per_sec"))
    					{
    						Double value = Double.parseDouble(row.split(":")[1]);
    						redisInfo.getStats().setInstantaneous_ops_per_sec(value);
    					}
    					
    					if(key.equals("master_last_io_seconds_ago"))
    					{
    						Double value = Double.parseDouble(row.split(":")[1]);
    						redisInfo.getReplication().setMaster_last_io_seconds_ago(value);
    					}
    					
    					if(key.equals("master_sync_in_progress"))
    					{
    						Double value = Double.parseDouble(row.split(":")[1]);
    						redisInfo.getReplication().setMaster_sync_in_progress(value);
    					}	
    					
    					if(key.equals("connected_slaves"))
    					{
    						Double value = Double.parseDouble(row.split(":")[1]);
    						redisInfo.getReplication().setConnected_slaves(value);
    					}	
    					
    					if(key.equals("rdb_bgsave_in_progress"))
    					{
    						Double value = Double.parseDouble(row.split(":")[1]);
    						redisInfo.getPersistence().setRdb_bgsave_in_progress(value);
    					}	
    					
    					if(key.equals("rdb_last_bgsave_time_sec"))
    					{
    						Double value = Double.parseDouble(row.split(":")[1]);
    						redisInfo.getPersistence().setRdb_last_bgsave_time_sec(value);
    					}	
    					
    					if(key.equals("rdb_changes_since_last_save"))
    					{
    						Double value = Double.parseDouble(row.split(":")[1]);
    						redisInfo.getPersistence().setRdb_changes_since_last_save(value);
    					}	
    					
    					//# Keyspace
    					//db0:keys=1299312,expires=1299312,avg_ttl=0
    					//db3:keys=1862,expires=1862,avg_ttl=0
    					
    					if(key.startsWith("db"))
    					{
    						numberOfKeys+= Double.parseDouble(row.split(":")[1].split(",")[0].split("=")[1]);
    					}
    			}
    				
    			redisInfo.getKeyspace().setKeys(numberOfKeys);		
    		}

	}

	public RedisInfo getRedisInfo() {
		return redisInfo;
	}

	public void setRedisInfo(RedisInfo redisInfo) {
		this.redisInfo = redisInfo;
	}

}
