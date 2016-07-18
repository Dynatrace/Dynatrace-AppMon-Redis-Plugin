package com.dynatrace.plugin.redis;

public class Replication {
	
	private double master_last_io_seconds_ago;
	private double master_sync_in_progress;
	private double connected_slaves = 0;
	
	public double getMaster_last_io_seconds_ago() {
		return master_last_io_seconds_ago;
	}
	public void setMaster_last_io_seconds_ago(double master_last_io_seconds_ago) {
		this.master_last_io_seconds_ago = master_last_io_seconds_ago;
	}
	public double getMaster_sync_in_progress() {
		return master_sync_in_progress;
	}
	public void setMaster_sync_in_progress(double master_sync_in_progress) {
		this.master_sync_in_progress = master_sync_in_progress;
	}
	public double getConnected_slaves() {
		return connected_slaves;
	}
	public void setConnected_slaves(double connected_slaves) {
		this.connected_slaves = connected_slaves;
	}

}
