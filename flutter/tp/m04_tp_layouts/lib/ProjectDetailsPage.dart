import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:m04_tp_layouts/entities/Project.dart';

class ProjectDetailsPage extends StatelessWidget {
  final Project project;

  const ProjectDetailsPage({required this.project});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        actionsPadding: EdgeInsets.only(right: 20),
        leading: Icon(Icons.folder),
        actions: [
          IconButton(icon: Icon(Icons.edit), onPressed: () {  },)
        ],
        title: Text(project.name),
        centerTitle: true,
        elevation: 2,
        shadowColor: Colors.black,
      ),
    );
  }

}