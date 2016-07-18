	package com.dynatrace.plugin.redis;

public class RedisInfo {

	private Memory memory;
	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}

	public Replication getReplication() {
		return replication;
	}

	public void setReplication(Replication replication) {
		this.replication = replication;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public Clients getClients() {
		return clients;
	}

	public void setClients(Clients clients) {
		this.clients = clients;
	}

	private Replication replication;
	private Stats stats;
	private Clients clients;
	private Persistence persistence;
	private Keyspace keyspace;
	
	public Keyspace getKeyspace() {
		return keyspace;
	}

	public void setKeyspace(Keyspace keyspace) {
		this.keyspace = keyspace;
	}

	public RedisInfo()
	{
		memory=new Memory();
		replication= new Replication();
		stats = new Stats();
		clients = new Clients();
		persistence = new Persistence();
		keyspace = new Keyspace();
	}

	public Persistence getPersistence() {
		return persistence;
	}

	public void setPersistence(Persistence persistence) {
		this.persistence = persistence;
	}
	
}
