/* データテーブル */
CREATE TABLE IF NOT EXISTS t_data (
	item_id VARCHAR(50) PRIMARY KEY
	, recipt_id VARCHAR(50)
	, buy_date DATE
	, category_id VARCHAR(50)
	, item_name VARCHAR(100)
	, shop_name VARCHAR(100)
	, spent_money DECIMAL
	, input_date DATE
	, update_date DATE
	, del_flg BOOLEAN
);

/* 費目マスタ */
CREATE TABLE IF NOT EXISTS m_category (
	category_id VARCHAR(50) PRIMARY KEY
	, category_name VARCHAR(100)
	, input_date DATE
	, update_date DATE
	, del_flg BOOLEAN
);