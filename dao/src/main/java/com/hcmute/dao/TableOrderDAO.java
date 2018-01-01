package com.hcmute.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TableOrderDAO {
	private Connection conn;
	private Statement statement;
	private ResultSet rs;
	
	
	public TableOrderDAO() {
		super();
		conn = DBConnection.getConnection();

	}

	void updateTable(String curentDate, String totalPrice, String MaKH, String tableID, String soHD) {
		try {
			conn = DBConnection.getConnection();
			String sql = "update chonban set NgayGioTra='" + curentDate + "' where MaBan=" + tableID
					+ " and MaKH = " + MaKH;
			statement = conn.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			conn.close();

			// Cập nhật lại tổng tiền trong bảng hoá đơn
			double trigia = Double.parseDouble(totalPrice);

			sql = "UPDATE hoadon SET TriGia =" + trigia + "WHERE SoHD = " + soHD;
			statement = conn.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			conn.close();

			// Lấy NgayGioDen của bàn
			conn = DBConnection.getConnection();
			sql = "SELECT NgayGioDen FROM chonban WHERE MaKH = " + MaKH + " AND NgayGioTra IS NULL";
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);

			String NgayGioDen = null;
			if (rs.next()) {
				NgayGioDen = rs.getString(1);
			}
			statement.close();
			conn.close();
			// Cập nhật bảng chonban báo bàn đã thanh toán
			conn = DBConnection.getConnection();
			sql = "UPDATE chonban SET NgayGioTra = '" + curentDate + "'WHERE MaKH = " + MaKH
					+ " AND NgayGioDen = " + NgayGioDen;
			statement = conn.createStatement();
			statement.executeUpdate(sql);

			statement.close();
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	
}
