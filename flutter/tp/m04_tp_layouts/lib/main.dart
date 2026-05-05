import 'package:flutter/material.dart';
import 'package:m04_tp_layouts/entities/Project.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(colorScheme: .fromSeed(seedColor: Colors.indigo)),
      home: MyHomePage(title: 'TP Layouts'),
    );
  }
}

class MyHomePage extends StatelessWidget {
  MyHomePage({super.key, required this.title});

  final String title;

  final List<Project> projects = [
    Project("Projet 1", "Spring boot"),
    Project("Projet 2", "Symfony + Twig"),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: Builder(
          builder: (BuildContext context) {
            return IconButton(
              icon: const Icon(Icons.rocket_launch),
              onPressed: null,
              color: Colors.white,
            );
          },
        ),
        backgroundColor: Colors.indigo,
        centerTitle: true,
        foregroundColor: Colors.white,
        title: Text(title),
      ),
      bottomNavigationBar: NavigationBar(
        destinations: const <Widget>[
          NavigationDestination(icon: Icon(Icons.home), label: 'Accueil'),
          NavigationDestination(
            icon: Icon(Icons.folder_copy),
            label: 'Projets',
          ),
          NavigationDestination(icon: Icon(Icons.person), label: 'Profil'),
        ],
      ),
      body: Column(
        children: [
          Padding(padding: const EdgeInsets.all(16.0)),
          Text(
            'Liste de projets',
            style: TextStyle(fontWeight: FontWeight(700), fontSize: 24),
          ),
          Padding(padding: const EdgeInsets.all(16.0)),
          Expanded(
            child: ListView.builder(
              itemCount: projects.length,
              itemBuilder: (BuildContext context, int index) {
                return Card(
                  color: Colors.black,
                  elevation: 4,
                  child: ListTile(
                    leading: Icon(Icons.folder),
                    title: Text(
                      projects[index].title,
                      style: TextStyle(color: Colors.white),
                    ),
                    subtitle: Text(
                      projects[index].description,
                      style: TextStyle(color: Colors.grey),
                    ),
                    trailing: Icon(Icons.chevron_right),
                  ),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}

// class ProjectCard extends StatelessWidget {
//   final Project project;
//
//   ProjectCard({super.key, required this.project});
//
//   @override
//   Widget build(BuildContext context) {
//     return Card()
//   }
//
//
// }
