-- Initial schema
CREATE TABLE account_transactions
(
  transaction_id SERIAL PRIMARY KEY NOT NULL,
  group_id INT,
  from_id SMALLINT NOT NULL,
  to_id SMALLINT NOT NULL,
  amount DOUBLE PRECISION NOT NULL,
  create_date TIMESTAMP DEFAULT now() NOT NULL,
  applied BOOL NOT NULL
);
CREATE TABLE accounts
(
  account_id SMALLINT PRIMARY KEY NOT NULL,
  company_id NUMERIC(13) NOT NULL,
  code VARCHAR(50) NOT NULL,
  account_type VARCHAR(100) NOT NULL,
  display_name VARCHAR(500) NOT NULL,
  parent_id INT,
  is_group BOOL,
  unit_id SMALLINT NOT NULL
);
CREATE TABLE activity_codes
(
  code VARCHAR(10) PRIMARY KEY NOT NULL,
  display_name VARCHAR(500) NOT NULL,
  enabled BOOL NOT NULL DEFAULT TRUE
);
CREATE TABLE addresses
(
  address_id SERIAL PRIMARY KEY NOT NULL,
  location_id UUID NOT NULL,
  zip_code INT NOT NULL,
  street_name VARCHAR(200) NOT NULL,
  street_number INT NOT NULL,
  numeric_suffix INT NOT NULL,
  letter_suffix VARCHAR(5) NOT NULL,
  apartment_number INT NOT NULL,
  apartment_type INT NOT NULL
);
CREATE TABLE applied_taxes
(
  company_id NUMERIC(13) NOT NULL,
  tax_id INT NOT NULL,
  rate_override DOUBLE PRECISION,
  PRIMARY KEY (company_id, tax_id)
);
CREATE TYPE AREA_TYPE AS ENUM ('region', 'area');
CREATE TABLE areas
(
  area_id UUID PRIMARY KEY NOT NULL,
  region_id SMALLINT NOT NULL,
  country_id SMALLINT NOT NULL,
  display_name VARCHAR(500) NOT NULL,
  level AREA_TYPE NOT NULL
);
CREATE TABLE assigned_tax_authorities
(
  company_id NUMERIC(13) NOT NULL,
  authority_id INT NOT NULL,
  from_date DATE NOT NULL,
  to_date DATE NOT NULL,
  PRIMARY KEY (company_id, authority_id)
);
CREATE TABLE attorneys
(
  attorney_id SERIAL PRIMARY KEY NOT NULL,
  company_id NUMERIC(13) NOT NULL,
  employee_id INT NOT NULL,
  attorney_type VARCHAR(100) NOT NULL,
  from_date DATE NOT NULL,
  duration INT,
  to_date DATE,
  transferable BOOL NOT NULL,
  template_id INT NOT NULL
);
CREATE TABLE bank_accounts
(
  account_id SERIAL PRIMARY KEY NOT NULL,
  bank_id INT NOT NULL,
  counterparty_id INT NOT NULL,
  account_type VARCHAR(100) NOT NULL,
  number NUMERIC(20) NOT NULL
);
CREATE TABLE banks
(
  bank_id INT PRIMARY KEY NOT NULL,
  company_id NUMERIC(13) NOT NULL,
  correspondent_account_number NUMERIC(20) NOT NULL,
  parent_id INT
);
CREATE TABLE bill_contents
(
  bill_id INT NOT NULL,
  product_id INT NOT NULL,
  position INT NOT NULL,
  quantity DOUBLE PRECISION NOT NULL,
  PRIMARY KEY (bill_id, product_id, position)
);
CREATE TABLE bills
(
  bill_id SERIAL PRIMARY KEY NOT NULL,
  custom_number VARCHAR(100),
  from_id INT NOT NULL,
  to_id INT NOT NULL,
  expiry_date DATE NOT NULL
);

CREATE TABLE budget_administrators
(
  code NUMERIC(3) PRIMARY KEY NOT NULL CHECK (code > 0),
  display_name VARCHAR(200) NOT NULL
);

CREATE TABLE budget_classification_codes
(
  code NUMERIC(17) PRIMARY KEY NOT NULL CHECK (code > 0),
  display_name VARCHAR(1000) NOT NULL,
  level SMALLINT NOT NULL DEFAULT 1
);
CREATE TABLE budget_payment_reasons
(
  reason_id SERIAL PRIMARY KEY NOT NULL,
  name VARCHAR(100) NOT NULL,
  display_name VARCHAR(500) NOT NULL
);
CREATE TABLE budget_payments
(
  payment_id INT PRIMARY KEY NOT NULL,
  budget_administrator_code NUMERIC(3) NOT NULL,
  budget_classification_code NUMERIC(17) NOT NULL,
  reason_id INT NOT NULL,
  fiscal_period VARCHAR(500) NOT NULL,
  document_id INT,
  document_date DATE
);
CREATE TABLE commands
(
  command_id SERIAL PRIMARY KEY NOT NULL,
  company_id NUMERIC(13) NOT NULL,
  employee_id INT NOT NULL,
  create_date TIMESTAMP DEFAULT now() NOT NULL,
  command_type VARCHAR(100) NOT NULL,
  template_id INT NOT NULL
);
CREATE TABLE companies 
(
  company_id NUMERIC(13) PRIMARY KEY CHECK (company_id > 0),
  counterparty_id INT NOT NULL,
  short_name VARCHAR(100),
  full_name VARCHAR(500),
  display_name VARCHAR(500) NOT NULL,
  tax_number NUMERIC(12) CHECK (tax_number > 0),
  fiscal_code NUMERIC(9) CHECK (fiscal_code > 0),
  registration_date DATE,
  fiscal_authority_code NUMERIC(4),
  fiscal_authority_id INT,
  pension_fund_number VARCHAR(14),
  pension_fund_id INT,
  social_insurance_fund_number NUMERIC(12),
  social_insurance_fund_id INT,
  type_code NUMERIC(5),
  property_form NUMERIC(2),
  state_authority_code NUMERIC(7) CHECK (state_authority_code > 0),
  main_activity VARCHAR(10),
  statistics_code NUMERIC(10) CHECK (statistics_code > 0),
  statistics_branch_id INT,
  municipality_id NUMERIC(11) CHECK (municipality_id > 0),
  main_currency_id SMALLINT NOT NULL
);
CREATE TABLE company_type_codes
(
  code NUMERIC(5) PRIMARY KEY NOT NULL,
  display_name VARCHAR(500) NOT NULL
);
CREATE TABLE contracts
(
  contract_id SERIAL PRIMARY KEY NOT NULL,
  issuer_id INT NOT NULL,
  signer_id INT NOT NULL,
  contract_type VARCHAR(100) NOT NULL,
  create_date TIMESTAMP DEFAULT now() NOT NULL,
  template_id INT NOT NULL
);
CREATE TYPE counterparty_type AS ENUM ('company', 'person', 'employee');
CREATE TABLE counterparties
(
  counterparty_id SERIAL PRIMARY KEY NOT NULL,
  counterparty_type counterparty_type NOT NULL
);
CREATE TABLE counterparty_addresses
(
  counterparty_id INT NOT NULL,
  type VARCHAR(100) NOT NULL,
  address_id INT NOT NULL,
  PRIMARY KEY (counterparty_id, type, address_id)
);
CREATE TABLE counterparty_audit
(
  event_id INT NOT NULL,
  counterparty_id INT NOT NULL,
  key VARCHAR(100) NOT NULL,
  value VARCHAR(500) NOT NULL,
  from_date DATE NOT NULL,
  to_date DATE NOT NULL,
  PRIMARY KEY (event_id, counterparty_id, key, from_date, to_date)
);
CREATE TABLE counterparty_currencies
(
  counterparty_id INT NOT NULL,
  currency_id SMALLINT NOT NULL,
  PRIMARY KEY (counterparty_id, currency_id)
);
CREATE TABLE counterparty_settings
(
  counterparty_id INT NOT NULL,
  key VARCHAR(100) NOT NULL,
  value VARCHAR(500) NOT NULL,
  PRIMARY KEY (counterparty_id, key)
);
CREATE TABLE countries
(
  country_id SMALLINT PRIMARY KEY NOT NULL,
  code CHAR(3) NOT NULL,
  display_name VARCHAR(500) NOT NULL
);
CREATE TABLE currencies
(
  currency_id SMALLINT PRIMARY KEY NOT NULL,
  code CHAR(3) NOT NULL,
  display_name VARCHAR(500) NOT NULL
);
CREATE TABLE currencies_exchange
(
  from_id SMALLINT NOT NULL,
  to_id SMALLINT NOT NULL,
  actual_on DATE DEFAULT current_date NOT NULL,
  rate DOUBLE PRECISION NOT NULL,
  PRIMARY KEY (from_id, to_id, actual_on)
);
CREATE TABLE departments
(
  department_id SERIAL PRIMARY KEY NOT NULL,
  company_id NUMERIC(13) NOT NULL,
  name VARCHAR(100) NOT NULL,
  display_name VARCHAR(500) NOT NULL,
  parent_id INT NOT NULL
);
CREATE TABLE document_templates
(
  template_id SERIAL PRIMARY KEY NOT NULL,
  counterparty_id INT NOT NULL,
  name VARCHAR(100) NOT NULL,
  display_name VARCHAR(500) NOT NULL,
  path VARCHAR(1000) NOT NULL
);
CREATE TABLE employees
(
  employee_id SERIAL PRIMARY KEY NOT NULL,
  company_id NUMERIC(13) NOT NULL,
  counterparty_id INT NOT NULL,
  person_id INT NOT NULL,
  post_id INT NOT NULL,
  display_name VARCHAR(500) NOT NULL,
  gross_pay DOUBLE PRECISION NOT NULL,
  advance_percent DOUBLE PRECISION NOT NULL,
  employment_status INT NOT NULL,
  hired_date DATE DEFAULT current_date NOT NULL,
  fired_date DATE,
  visible BOOL DEFAULT true NOT NULL
);
CREATE TABLE event_flags
(
  event_id INT NOT NULL,
  flag_id INT NOT NULL,
  PRIMARY KEY (event_id, flag_id)
);
CREATE TABLE events
(
  event_id SERIAL PRIMARY KEY NOT NULL,
  event_type VARCHAR(100) NOT NULL,
  company_id NUMERIC(13) NOT NULL,
  create_date TIMESTAMP DEFAULT now() NOT NULL,
  priority INT NOT NULL,
  status VARCHAR(100) NOT NULL
);
CREATE TABLE flags
(
  flag_id SERIAL PRIMARY KEY NOT NULL,
  company_id NUMERIC(13) NOT NULL,
  name VARCHAR(100) NOT NULL,
  display_name VARCHAR(500) NOT NULL
);
CREATE TABLE identity_cards
(
  identity_card_id SERIAL PRIMARY KEY NOT NULL,
  type INT NOT NULL,
  series INT NOT NULL,
  number INT NOT NULL,
  issuer VARCHAR(200) NOT NULL,
  issuer_code VARCHAR(7) NOT NULL,
  issue_date DATE NOT NULL,
  valid_until DATE
);
CREATE TABLE invoices
(
  invoice_id SERIAL PRIMARY KEY NOT NULL,
  bill_id INT NOT NULL,
  create_date TIMESTAMP DEFAULT now() NOT NULL
);

CREATE TABLE locations
(
  location_id UUID PRIMARY KEY NOT NULL, 
  display_name VARCHAR(500) NOT NULL,
  area_id UUID NOT NULL,
  region_id SMALLINT NOT NULL,
  country_id SMALLINT NOT NULL,
  zip_code NUMERIC(6) CHECK (zip_code > 0),
  municipality_id NUMERIC(11) CHECK (municipality_id > 0),
  fiscal_authority_code NUMERIC(4) CHECK (fiscal_authority_code > 0)
);
CREATE TABLE payment_types
(
  payment_type_id INT PRIMARY KEY NOT NULL,
  name VARCHAR(100) NOT NULL,
  display_name VARCHAR(500) NOT NULL
);
CREATE TABLE payments
(
  payment_id SERIAL PRIMARY KEY NOT NULL,
  from_account_id INT NOT NULL,
  to_account_id INT NOT NULL,
  document_type INT NOT NULL,
  payment_type INT NOT NULL,
  status INT NOT NULL,
  amount DOUBLE PRECISION NOT NULL,
  create_date TIMESTAMP DEFAULT now() NOT NULL
);
CREATE TABLE persons
(
  person_id SERIAL PRIMARY KEY NOT NULL,
  counterparty_id INT NOT NULL,
  company_id NUMERIC(13) NOT NULL,
  name VARCHAR(100) NOT NULL,
  surname VARCHAR(100) NOT NULL,
  parental_name VARCHAR(100),
  mark VARCHAR(100),
  identity_card_id INT NOT NULL,
  birthplace_id UUID NOT NULL,
  birth_date DATE NOT NULL,
  citizenship_id INT NOT NULL,
  work_phone VARCHAR(100),
  home_phone VARCHAR(100)
);
CREATE TABLE posts
(
  post_id INT PRIMARY KEY NOT NULL,
  post_name VARCHAR(100) NOT NULL,
  display_name VARCHAR(500) NOT NULL,
  company_id NUMERIC(13) NOT NULL
);
CREATE TABLE product_groups
(
  group_id SERIAL PRIMARY KEY NOT NULL,
  company_id NUMERIC(13) NOT NULL,
  name VARCHAR(100) NOT NULL,
  display_name VARCHAR(500) NOT NULL,
  parent_group_id INT
);
CREATE TABLE products
(
  product_id INT PRIMARY KEY NOT NULL,
  company_id NUMERIC(13) NOT NULL,
  name VARCHAR(100) NOT NULL,
  display_name VARCHAR(500) NOT NULL,
  price DOUBLE PRECISION NOT NULL,
  unit_id SMALLINT NOT NULL,
  group_id INT NOT NULL
);
CREATE TABLE property_form_codes
(
  code NUMERIC(2) PRIMARY KEY NOT NULL,
  display_name VARCHAR(500) NOT NULL
);
CREATE TABLE reference_documents
(
  reference_id SERIAL PRIMARY KEY NOT NULL,
  company_id NUMERIC(13) NOT NULL,
  reference_type VARCHAR(100) NOT NULL,
  create_date TIMESTAMP DEFAULT now() NOT NULL,
  user_id INT NOT NULL
);
CREATE TABLE report_data
(
  report_id INT PRIMARY KEY NOT NULL,
  data VARCHAR NOT NULL
);
CREATE TABLE reports
(
  report_id INT PRIMARY KEY NOT NULL,
  company_id NUMERIC(13) NOT NULL,
  name VARCHAR(100) NOT NULL,
  display_name VARCHAR(500) NOT NULL,
  type VARCHAR(100) NOT NULL,
  status VARCHAR(100) NOT NULL,
  create_date TIMESTAMP DEFAULT now() NOT NULL,
  query VARCHAR(1000) NOT NULL
);
CREATE TABLE roles
(
  role_id SERIAL PRIMARY KEY NOT NULL,
  role_name VARCHAR(100) NOT NULL,
  display_name VARCHAR(500) NOT NULL
);
CREATE TABLE salary
(
  salary_id SERIAL PRIMARY KEY NOT NULL,
  employee_id INT NOT NULL,
  currency_id SMALLINT NOT NULL,
  salary_type VARCHAR(100) NOT NULL,
  way VARCHAR(100) NOT NULL,
  year INT NOT NULL,
  month INT NOT NULL,
  amount DOUBLE PRECISION NOT NULL,
  user_id INT
);
CREATE TABLE settings
(
  key VARCHAR(50) PRIMARY KEY NOT NULL,
  value VARCHAR(500) NOT NULL
);
CREATE TABLE state_authority_codes
(
  code NUMERIC(7) PRIMARY KEY NOT NULL,
  display_name VARCHAR(500) NOT NULL
);
CREATE TABLE tax_authorities
(
  authority_id INT PRIMARY KEY NOT NULL,
  company_id NUMERIC(13) NOT NULL
);
CREATE TABLE tax_deductions
(
  deduction_id SERIAL PRIMARY KEY NOT NULL,
  tax_id INT NOT NULL,
  company_id NUMERIC(13) NOT NULL,
  name VARCHAR(100) NOT NULL,
  display_name VARCHAR(500) NOT NULL,
  amount DOUBLE PRECISION NOT NULL
);
CREATE TABLE tax_types
(
  type_id SERIAL PRIMARY KEY NOT NULL,
  name VARCHAR(100) NOT NULL,
  display_name VARCHAR(500) NOT NULL
);
CREATE TABLE taxes
(
  tax_id SERIAL PRIMARY KEY NOT NULL,
  company_id NUMERIC(13) NOT NULL,
  name VARCHAR(100) NOT NULL,
  display_name VARCHAR(500) NOT NULL,
  time_INTerval VARCHAR(100) NOT NULL,
  tax_type INT NOT NULL,
  rate DOUBLE PRECISION NOT NULL
);
CREATE TABLE units
(
  unit_id SMALLINT PRIMARY KEY NOT NULL,
  short_name VARCHAR(10) NOT NULL,
  display_name VARCHAR(100) NOT NULL
);
CREATE TABLE user_roles
(
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (user_id, role_id)
);
CREATE TABLE users
(
  user_id SERIAL PRIMARY KEY NOT NULL,
  login VARCHAR(100) NOT NULL,
  password CHAR(128) NOT NULL,
  salt CHAR(128) NOT NULL,
  person_id INT
);
CREATE TABLE warehouse_audit
(
  event_id SERIAL PRIMARY KEY NOT NULL,
  warehouse_id INT NOT NULL,
  event_type VARCHAR(100) NOT NULL,
  create_date TIMESTAMP DEFAULT now() NOT NULL
);
CREATE TABLE warehouse_audit_details
(
  event_id INT NOT NULL,
  product_id INT NOT NULL,
  quantity DOUBLE PRECISION NOT NULL,
  comments VARCHAR(500) NOT NULL,
  PRIMARY KEY (event_id, product_id)
);
CREATE TABLE warehouse_state
(
  warehouse_id INT NOT NULL,
  product_id INT NOT NULL,
  quantity DOUBLE PRECISION NOT NULL,
  PRIMARY KEY (warehouse_id, product_id, quantity)
);
CREATE TABLE warehouses
(
  warehouse_id SERIAL PRIMARY KEY NOT NULL,
  company_id NUMERIC(13) NOT NULL,
  location_id UUID NOT NULL,
  name VARCHAR(100) NOT NULL,
  display_name VARCHAR(500) NOT NULL
);
ALTER TABLE account_transactions ADD FOREIGN KEY (from_id) REFERENCES accounts (account_id) ON DELETE CASCADE;
ALTER TABLE account_transactions ADD FOREIGN KEY (to_id) REFERENCES accounts (account_id) ON DELETE CASCADE;
ALTER TABLE accounts ADD FOREIGN KEY (parent_id) REFERENCES accounts (account_id) ON DELETE SET NULL;
ALTER TABLE accounts ADD FOREIGN KEY (company_id) REFERENCES companies (company_id) ON DELETE CASCADE;
ALTER TABLE accounts ADD FOREIGN KEY (unit_id) REFERENCES units (unit_id) ON DELETE CASCADE;
ALTER TABLE addresses ADD FOREIGN KEY (location_id) REFERENCES locations (location_id) ON DELETE CASCADE;
ALTER TABLE applied_taxes ADD FOREIGN KEY (company_id) REFERENCES companies (company_id) ON DELETE CASCADE;
ALTER TABLE areas ADD FOREIGN KEY (country_id) REFERENCES countries (country_id) ON DELETE CASCADE;
CREATE INDEX areas_level ON areas (level);
CREATE INDEX areas_region_id ON areas (region_id);
CREATE INDEX areas_country_id ON areas (country_id);
ALTER TABLE assigned_tax_authorities ADD FOREIGN KEY (company_id) REFERENCES companies (company_id) ON DELETE CASCADE;
ALTER TABLE assigned_tax_authorities ADD FOREIGN KEY (authority_id) REFERENCES tax_authorities (authority_id) ON DELETE CASCADE;
ALTER TABLE attorneys ADD FOREIGN KEY (company_id) REFERENCES companies (company_id) ON DELETE CASCADE;
ALTER TABLE attorneys ADD FOREIGN KEY (employee_id) REFERENCES employees (employee_id) ON DELETE CASCADE;
ALTER TABLE bank_accounts ADD FOREIGN KEY (bank_id) REFERENCES banks (bank_id) ON DELETE CASCADE;
ALTER TABLE bank_accounts ADD FOREIGN KEY (counterparty_id) REFERENCES counterparties (counterparty_id) ON DELETE CASCADE;
ALTER TABLE banks ADD FOREIGN KEY (parent_id) REFERENCES banks (bank_id) ON DELETE CASCADE;
ALTER TABLE bill_contents ADD FOREIGN KEY (bill_id) REFERENCES bills (bill_id) ON DELETE CASCADE;
ALTER TABLE bill_contents ADD FOREIGN KEY (product_id) REFERENCES products (product_id);
ALTER TABLE bills ADD FOREIGN KEY (from_id) REFERENCES companies (company_id) ON DELETE CASCADE;
ALTER TABLE bills ADD FOREIGN KEY (to_id) REFERENCES companies (company_id) ON DELETE CASCADE;
CREATE UNIQUE INDEX unique_custom_number ON bills (custom_number);
ALTER TABLE budget_payments ADD FOREIGN KEY (budget_classification_code) REFERENCES budget_classification_codes (code);
ALTER TABLE budget_payments ADD FOREIGN KEY (budget_administrator_code) REFERENCES budget_administrators (code);
ALTER TABLE budget_payments ADD FOREIGN KEY (reason_id) REFERENCES budget_payment_reasons (reason_id);
ALTER TABLE budget_payments ADD FOREIGN KEY (payment_id) REFERENCES payments (payment_id);
ALTER TABLE commands ADD FOREIGN KEY (company_id) REFERENCES companies (company_id);
ALTER TABLE commands ADD FOREIGN KEY (employee_id) REFERENCES employees (employee_id);
ALTER TABLE companies ADD FOREIGN KEY (main_activity) REFERENCES activity_codes (code);
ALTER TABLE companies ADD FOREIGN KEY (fiscal_authority_id) REFERENCES companies (company_id);
ALTER TABLE companies ADD FOREIGN KEY (pension_fund_id) REFERENCES companies (company_id);
ALTER TABLE companies ADD FOREIGN KEY (social_insurance_fund_id) REFERENCES companies (company_id);
ALTER TABLE companies ADD FOREIGN KEY (statistics_branch_id) REFERENCES companies (company_id);
ALTER TABLE companies ADD FOREIGN KEY (type_code) REFERENCES company_type_codes (code);
ALTER TABLE companies ADD FOREIGN KEY (state_authority_code) REFERENCES state_authority_codes (code);
ALTER TABLE companies ADD FOREIGN KEY (counterparty_id) REFERENCES counterparties (counterparty_id);
ALTER TABLE companies ADD FOREIGN KEY (main_currency_id) REFERENCES currencies (currency_id);
ALTER TABLE companies ADD FOREIGN KEY (property_form) REFERENCES property_form_codes (code);
ALTER TABLE contracts ADD FOREIGN KEY (issuer_id) REFERENCES counterparties (counterparty_id);
ALTER TABLE contracts ADD FOREIGN KEY (signer_id) REFERENCES counterparties (counterparty_id);
ALTER TABLE counterparty_addresses ADD FOREIGN KEY (address_id) REFERENCES addresses (address_id);
ALTER TABLE counterparty_addresses ADD FOREIGN KEY (counterparty_id) REFERENCES counterparties (counterparty_id);
ALTER TABLE counterparty_audit ADD FOREIGN KEY (counterparty_id) REFERENCES counterparties (counterparty_id);
ALTER TABLE counterparty_currencies ADD FOREIGN KEY (counterparty_id) REFERENCES counterparties (counterparty_id);
ALTER TABLE counterparty_currencies ADD FOREIGN KEY (currency_id) REFERENCES currencies (currency_id);
ALTER TABLE counterparty_settings ADD FOREIGN KEY (counterparty_id) REFERENCES counterparties (counterparty_id);
CREATE UNIQUE INDEX unique_iso_code ON countries (code);
CREATE UNIQUE INDEX code_index ON currencies (code);
ALTER TABLE currencies_exchange ADD FOREIGN KEY (from_id) REFERENCES currencies (currency_id);
ALTER TABLE currencies_exchange ADD FOREIGN KEY (to_id) REFERENCES currencies (currency_id);
ALTER TABLE departments ADD FOREIGN KEY (company_id) REFERENCES companies (company_id);
ALTER TABLE document_templates ADD FOREIGN KEY (counterparty_id) REFERENCES counterparties (counterparty_id);
ALTER TABLE employees ADD FOREIGN KEY (company_id) REFERENCES companies (company_id);
ALTER TABLE employees ADD FOREIGN KEY (counterparty_id) REFERENCES counterparties (counterparty_id) ON DELETE CASCADE;
ALTER TABLE employees ADD FOREIGN KEY (person_id) REFERENCES persons (person_id) ON DELETE CASCADE;
ALTER TABLE employees ADD FOREIGN KEY (post_id) REFERENCES posts (post_id) ON DELETE CASCADE;
ALTER TABLE event_flags ADD FOREIGN KEY (event_id) REFERENCES events (event_id) ON DELETE CASCADE;
ALTER TABLE event_flags ADD FOREIGN KEY (flag_id) REFERENCES flags (flag_id) ON DELETE CASCADE;
ALTER TABLE events ADD FOREIGN KEY (company_id) REFERENCES companies (company_id) ON DELETE CASCADE;
ALTER TABLE flags ADD FOREIGN KEY (company_id) REFERENCES companies (company_id) ON DELETE CASCADE;
ALTER TABLE invoices ADD FOREIGN KEY (bill_id) REFERENCES bills (bill_id) ON DELETE CASCADE;
ALTER TABLE locations ADD FOREIGN KEY (area_id) REFERENCES areas (area_id);
ALTER TABLE locations ADD FOREIGN KEY (country_id) REFERENCES countries (country_id);
CREATE INDEX locations_zip_code ON locations (zip_code);
CREATE INDEX locations_municipality_id ON locations (municipality_id);
CREATE INDEX locations_fiscal_authority_code ON locations (fiscal_authority_code);
ALTER TABLE payments ADD FOREIGN KEY (from_account_id) REFERENCES bank_accounts (account_id) ON DELETE CASCADE;
ALTER TABLE payments ADD FOREIGN KEY (to_account_id) REFERENCES bank_accounts (account_id) ON DELETE CASCADE;
ALTER TABLE payments ADD FOREIGN KEY (payment_type) REFERENCES payment_types (payment_type_id) ON DELETE CASCADE;
ALTER TABLE persons ADD FOREIGN KEY (company_id) REFERENCES companies (company_id) ON DELETE CASCADE;
ALTER TABLE persons ADD FOREIGN KEY (counterparty_id) REFERENCES counterparties (counterparty_id) ON DELETE CASCADE;
ALTER TABLE persons ADD FOREIGN KEY (citizenship_id) REFERENCES countries (country_id);
ALTER TABLE persons ADD FOREIGN KEY (identity_card_id) REFERENCES identity_cards (identity_card_id) ON DELETE CASCADE;
ALTER TABLE persons ADD FOREIGN KEY (birthplace_id) REFERENCES locations (location_id);
ALTER TABLE posts ADD FOREIGN KEY (company_id) REFERENCES companies (company_id) ON DELETE CASCADE;
ALTER TABLE product_groups ADD FOREIGN KEY (company_id) REFERENCES companies (company_id) ON DELETE CASCADE;
ALTER TABLE product_groups ADD FOREIGN KEY (parent_group_id) REFERENCES product_groups (group_id) ON DELETE CASCADE;
ALTER TABLE products ADD FOREIGN KEY (company_id) REFERENCES companies (company_id) ON DELETE CASCADE;
ALTER TABLE products ADD FOREIGN KEY (group_id) REFERENCES product_groups (group_id) ON DELETE CASCADE;
ALTER TABLE products ADD FOREIGN KEY (unit_id) REFERENCES units (unit_id) ON DELETE CASCADE;
ALTER TABLE reference_documents ADD FOREIGN KEY (company_id) REFERENCES companies (company_id) ON DELETE CASCADE;
ALTER TABLE reference_documents ADD FOREIGN KEY (user_id) REFERENCES users (user_id);
ALTER TABLE report_data ADD FOREIGN KEY (report_id) REFERENCES reports (report_id) ON DELETE CASCADE;
ALTER TABLE reports ADD FOREIGN KEY (company_id) REFERENCES companies (company_id) ON DELETE CASCADE;
ALTER TABLE salary ADD FOREIGN KEY (currency_id) REFERENCES currencies (currency_id);
ALTER TABLE salary ADD FOREIGN KEY (employee_id) REFERENCES employees (employee_id) ON DELETE CASCADE;
ALTER TABLE salary ADD FOREIGN KEY (user_id) REFERENCES users (user_id);
ALTER TABLE tax_deductions ADD FOREIGN KEY (company_id) REFERENCES companies (company_id) ON DELETE CASCADE;
ALTER TABLE tax_deductions ADD FOREIGN KEY (tax_id) REFERENCES taxes (tax_id);
ALTER TABLE taxes ADD FOREIGN KEY (company_id) REFERENCES companies (company_id);
ALTER TABLE taxes ADD FOREIGN KEY (tax_type) REFERENCES tax_types (type_id);
ALTER TABLE user_roles ADD FOREIGN KEY (role_id) REFERENCES roles (role_id) ON DELETE CASCADE;
ALTER TABLE user_roles ADD FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE;
ALTER TABLE users ADD FOREIGN KEY (person_id) REFERENCES persons (person_id) ON DELETE SET NULL;
ALTER TABLE warehouse_audit ADD FOREIGN KEY (warehouse_id) REFERENCES warehouses (warehouse_id) ON DELETE CASCADE;
ALTER TABLE warehouse_audit_details ADD FOREIGN KEY (product_id) REFERENCES products (product_id) ON DELETE CASCADE;
ALTER TABLE warehouse_audit_details ADD FOREIGN KEY (event_id) REFERENCES warehouse_audit (event_id) ON DELETE CASCADE;
ALTER TABLE warehouse_state ADD FOREIGN KEY (product_id) REFERENCES products (product_id) ON DELETE CASCADE;
ALTER TABLE warehouse_state ADD FOREIGN KEY (warehouse_id) REFERENCES warehouses (warehouse_id) ON DELETE CASCADE;
ALTER TABLE warehouses ADD FOREIGN KEY (company_id) REFERENCES companies (company_id) ON DELETE CASCADE;
ALTER TABLE warehouses ADD FOREIGN KEY (location_id) REFERENCES locations (location_id) ON DELETE CASCADE;