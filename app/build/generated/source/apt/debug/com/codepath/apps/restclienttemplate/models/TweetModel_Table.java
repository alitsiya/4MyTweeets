package com.codepath.apps.restclienttemplate.models;

import android.content.ContentValues;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.OperatorGroup;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;
import java.lang.Class;
import java.lang.IllegalArgumentException;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;

/**
 * This is generated code. Please do not modify */
public final class TweetModel_Table extends ModelAdapter<TweetModel> {
  /**
   * Primary Key */
  public static final Property<Long> id = new Property<Long>(TweetModel.class, "id");

  public static final Property<String> body = new Property<String>(TweetModel.class, "body");

  public static final Property<Long> uid = new Property<Long>(TweetModel.class, "uid");

  public static final Property<String> createdAt = new Property<String>(TweetModel.class, "createdAt");

  public static final Property<String> userName = new Property<String>(TweetModel.class, "userName");

  public static final Property<Long> userUid = new Property<Long>(TweetModel.class, "userUid");

  public static final Property<String> screenName = new Property<String>(TweetModel.class, "screenName");

  public static final Property<String> profileImageUrl = new Property<String>(TweetModel.class, "profileImageUrl");

  public static final IProperty[] ALL_COLUMN_PROPERTIES = new IProperty[]{id,body,uid,createdAt,userName,userUid,screenName,profileImageUrl};

  public TweetModel_Table(DatabaseDefinition databaseDefinition) {
    super(databaseDefinition);
  }

  @Override
  public final Class<TweetModel> getModelClass() {
    return TweetModel.class;
  }

  @Override
  public final String getTableName() {
    return "`TweetModel`";
  }

  @Override
  public final TweetModel newInstance() {
    return new TweetModel();
  }

  @Override
  public final Property getProperty(String columnName) {
    columnName = QueryBuilder.quoteIfNeeded(columnName);
    switch ((columnName)) {
      case "`id`":  {
        return id;
      }
      case "`body`":  {
        return body;
      }
      case "`uid`":  {
        return uid;
      }
      case "`createdAt`":  {
        return createdAt;
      }
      case "`userName`":  {
        return userName;
      }
      case "`userUid`":  {
        return userUid;
      }
      case "`screenName`":  {
        return screenName;
      }
      case "`profileImageUrl`":  {
        return profileImageUrl;
      }
      default: {
        throw new IllegalArgumentException("Invalid column name passed. Ensure you are calling the correct table's column");
      }
    }
  }

  @Override
  public final IProperty[] getAllColumnProperties() {
    return ALL_COLUMN_PROPERTIES;
  }

  @Override
  public final void bindToInsertValues(ContentValues values, TweetModel model) {
    values.put("`id`", model.id != null ? model.id : null);
    values.put("`body`", model.getBody() != null ? model.getBody() : null);
    values.put("`uid`", model.getUid());
    values.put("`createdAt`", model.getCreatedAt() != null ? model.getCreatedAt() : null);
    values.put("`userName`", model.getUserName() != null ? model.getUserName() : null);
    values.put("`userUid`", model.getUserUid());
    values.put("`screenName`", model.getScreenName() != null ? model.getScreenName() : null);
    values.put("`profileImageUrl`", model.getProfileImageUrl() != null ? model.getProfileImageUrl() : null);
  }

  @Override
  public final void bindToInsertStatement(DatabaseStatement statement, TweetModel model,
      int start) {
    statement.bindNumberOrNull(1 + start, model.id);
    statement.bindStringOrNull(2 + start, model.getBody());
    statement.bindLong(3 + start, model.getUid());
    statement.bindStringOrNull(4 + start, model.getCreatedAt());
    statement.bindStringOrNull(5 + start, model.getUserName());
    statement.bindLong(6 + start, model.getUserUid());
    statement.bindStringOrNull(7 + start, model.getScreenName());
    statement.bindStringOrNull(8 + start, model.getProfileImageUrl());
  }

  @Override
  public final void bindToUpdateStatement(DatabaseStatement statement, TweetModel model) {
    statement.bindNumberOrNull(1, model.id);
    statement.bindStringOrNull(2, model.getBody());
    statement.bindLong(3, model.getUid());
    statement.bindStringOrNull(4, model.getCreatedAt());
    statement.bindStringOrNull(5, model.getUserName());
    statement.bindLong(6, model.getUserUid());
    statement.bindStringOrNull(7, model.getScreenName());
    statement.bindStringOrNull(8, model.getProfileImageUrl());
    statement.bindNumberOrNull(9, model.id);
  }

  @Override
  public final void bindToDeleteStatement(DatabaseStatement statement, TweetModel model) {
    statement.bindNumberOrNull(1, model.id);
  }

  @Override
  public final String getCompiledStatementQuery() {
    return "INSERT INTO `TweetModel`(`id`,`body`,`uid`,`createdAt`,`userName`,`userUid`,`screenName`,`profileImageUrl`) VALUES (?,?,?,?,?,?,?,?)";
  }

  @Override
  public final String getUpdateStatementQuery() {
    return "UPDATE `TweetModel` SET `id`=?,`body`=?,`uid`=?,`createdAt`=?,`userName`=?,`userUid`=?,`screenName`=?,`profileImageUrl`=? WHERE `id`=?";
  }

  @Override
  public final String getDeleteStatementQuery() {
    return "DELETE FROM `TweetModel` WHERE `id`=?";
  }

  @Override
  public final String getCreationQuery() {
    return "CREATE TABLE IF NOT EXISTS `TweetModel`(`id` INTEGER, `body` TEXT, `uid` INTEGER, `createdAt` TEXT, `userName` TEXT, `userUid` INTEGER, `screenName` TEXT, `profileImageUrl` TEXT, PRIMARY KEY(`id`))";
  }

  @Override
  public final void loadFromCursor(FlowCursor cursor, TweetModel model) {
    model.id = cursor.getLongOrDefault("id");
    model.setBody(cursor.getStringOrDefault("body"));
    model.setUid(cursor.getLongOrDefault("uid"));
    model.setCreatedAt(cursor.getStringOrDefault("createdAt"));
    model.setUserName(cursor.getStringOrDefault("userName"));
    model.setUserUid(cursor.getLongOrDefault("userUid"));
    model.setScreenName(cursor.getStringOrDefault("screenName"));
    model.setProfileImageUrl(cursor.getStringOrDefault("profileImageUrl"));
  }

  @Override
  public final boolean exists(TweetModel model, DatabaseWrapper wrapper) {
    return SQLite.selectCountOf()
    .from(TweetModel.class)
    .where(getPrimaryConditionClause(model))
    .hasData(wrapper);
  }

  @Override
  public final OperatorGroup getPrimaryConditionClause(TweetModel model) {
    OperatorGroup clause = OperatorGroup.clause();
    clause.and(id.eq(model.id));
    return clause;
  }
}
