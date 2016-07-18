package com.dynatrace.plugin.redis;

public class Persistence {

	private double rdb_last_bgsave_time_sec;
	private double rdb_bgsave_in_progress;
	private double rdb_changes_since_last_save;

	public double getRdb_bgsave_in_progress() {
		return rdb_bgsave_in_progress;
	}

	public void setRdb_bgsave_in_progress(double rdb_bgsave_in_progress) {
		this.rdb_bgsave_in_progress = rdb_bgsave_in_progress;
	}

	public double getRdb_last_bgsave_time_sec() {
		return rdb_last_bgsave_time_sec;
	}

	public void setRdb_last_bgsave_time_sec(double rdb_last_bgsave_time_sec) {
		this.rdb_last_bgsave_time_sec = rdb_last_bgsave_time_sec;
	}

	public double getRdb_changes_since_last_save() {
		return rdb_changes_since_last_save;
	}

	public void setRdb_changes_since_last_save(double rdb_changes_since_last_save) {
		this.rdb_changes_since_last_save = rdb_changes_since_last_save;
	}
	
	
}
