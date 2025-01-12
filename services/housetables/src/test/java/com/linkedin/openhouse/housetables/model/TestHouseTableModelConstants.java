package com.linkedin.openhouse.housetables.model;

import com.linkedin.openhouse.housetables.api.spec.model.UserTable;
import com.linkedin.openhouse.housetables.dto.model.UserTableDto;
import lombok.Getter;

public class TestHouseTableModelConstants {
  private TestHouseTableModelConstants() {
    // Utility class, constructor does nothing
  }

  // constants for straightforward tests where there's singleton instance of a user table object
  // needed.
  private static final TestTuple tuple0 = new TestTuple(0);
  public static final String TEST_TABLE_ID = tuple0.getTableId();
  public static final String TEST_DB_ID = tuple0.getDatabaseId();
  public static final String TEST_TBL_VERSION = tuple0.getVer();
  public static final String TEST_TBL_META_LOC = tuple0.getTableLoc();

  public static final String TEST_DEFAULT_STORAGE_TYPE = "hdfs";

  public static final UserTableRow TEST_USER_TABLE_ROW = tuple0.get_userTableRow();
  public static final UserTableDto TEST_USER_TABLE_DTO = tuple0.get_userTableDto();
  public static final UserTable TEST_USER_TABLE = tuple0.get_userTable();

  // Model constants used for testing relatively more complicated cases.
  // table1, db0
  public static final TestTuple testTuple1_0 = new TestHouseTableModelConstants.TestTuple(1);
  // table2, db0
  public static final TestTuple testTuple2_0 = new TestHouseTableModelConstants.TestTuple(2);
  // table1, db1
  public static final TestTuple testTuple1_1 = new TestHouseTableModelConstants.TestTuple(1, 1);

  @Getter
  public static class TestTuple {
    private static final String LOC_TEMPLATE =
        "/openhouse/$test_db/$test_table/$version_metadata.json";
    private final UserTable _userTable;
    private final UserTableRow _userTableRow;
    private final UserTableDto _userTableDto;
    private final String ver;
    private final String tableId;
    private final String databaseId;
    private final String tableLoc;
    private final String storageType;

    public TestTuple(int tbSeq) {
      this(tbSeq, 0);
    }

    public TestTuple(int tbSeq, int dbSeq) {
      this.tableId = "test_table" + tbSeq;
      this.databaseId = "test_db" + dbSeq;
      this.ver =
          LOC_TEMPLATE
              .replace("$test_db", databaseId)
              .replace("$test_table", tableId)
              .replace("$version", "v0");
      this.tableLoc =
          LOC_TEMPLATE
              .replace("$test_db", databaseId)
              .replace("$test_table", tableId)
              .replace("$version", "v1");
      this.storageType = TEST_DEFAULT_STORAGE_TYPE;
      this._userTable =
          UserTable.builder()
              .tableId(tableId)
              .databaseId(databaseId)
              .tableVersion(ver)
              .metadataLocation(tableLoc)
              .storageType(storageType)
              .build();

      this._userTableDto =
          UserTableDto.builder()
              .tableId(tableId)
              .databaseId(databaseId)
              .tableVersion(ver)
              .metadataLocation(tableLoc)
              .storageType(storageType)
              .build();

      this._userTableRow =
          UserTableRow.builder()
              .tableId(tableId)
              .databaseId(databaseId)
              .version(1L)
              .metadataLocation(tableLoc)
              .storageType(storageType)
              .build();
    }
  }
}
