import 'package:flutter/material.dart';

enum ProjectStatus { enCours, termine, aVenir }

final class Project {
  String _name;
  String _desc;
  ProjectStatus _status;
  DateTime? _date;

  Project(
      {required String name,
        required String desc,
        ProjectStatus status = ProjectStatus.aVenir,
        DateTime? date})
      : _name = name,
        _desc = desc,
        _status = status,
        _date = date;

  set name(String value) {
    _name = value;
  }

  String get name => _name;

  set desc(String value) {
    _desc = value;
  }

  String get desc => _desc;

  set status(ProjectStatus value) {
    _status = value;
  }

  ProjectStatus get status => _status;

  set date(DateTime? date) {
    _date = date;
  }

  DateTime? get date => _date;
}
