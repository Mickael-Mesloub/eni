import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:m04_tp_layouts/ContributionPage.dart';
import 'package:m04_tp_layouts/EditProjectPage.dart';
import 'package:m04_tp_layouts/ProjectDetailsPage.dart';
import 'package:m04_tp_layouts/entities/Project.dart';

void main() {
  runApp(MyApp());
}

class ScreenArguments {
  final Project project;

  ScreenArguments(this.project);
}

class MyApp extends StatelessWidget {

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    final GoRouter _router = GoRouter(
        routes: [
          GoRoute(
              path: '/',
            builder: (context, state) => MyHomePage(title: 'Mes projets'),
            routes: [
              GoRoute(
                  path: '/details',
                  builder: (context, state) {
                    final args = state.extra as ScreenArguments;
                    return ProjectDetailsPage(project: args.project);
                  },
                routes: [
                  GoRoute(
                      path: '/edit',
                      builder: (context, state) {
                        final args = state.extra as ScreenArguments;
                        return EditProjectPage(project: args.project);
                      },
                )
              ]),
            ]
          ),

        ]
    );

    final colorScheme = ColorScheme.fromSeed(
      seedColor: Colors.indigo,
      primary: Colors.indigo[300],
      secondary: Colors.deepPurple,
    );

    return MaterialApp.router(
      title: 'Flutter Demo',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
          colorScheme: colorScheme,
          useMaterial3: true,
          scaffoldBackgroundColor: const Color(0xfff3f3f3),
          appBarTheme: AppBarTheme(
            backgroundColor: colorScheme.inversePrimary,
          ),
          textTheme: TextTheme(
              titleLarge: TextStyle(
                  fontSize: 24,
                  color: colorScheme.secondary
              )
          )
      ),
      routerConfig: _router,
    );
  }
}

class MyHomePage extends StatefulWidget {
  String title;

  List<Project> projects = [
    Project(name: 'projet 1', desc: 'la desciption'),
    Project(name: 'projet 2', desc: 'la desciption'),
  ];

  MyHomePage({required this.title});

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  int _currentIndex = 0;

  void _addProject() {
    setState(() {
      int num = widget.projects.length + 1;
      widget.projects.add(Project(name: 'projet $num', desc: 'new project'));
    });
  }

  void _onItemTapped(int index) {
    setState(() {
      _currentIndex = index;
    });
  }

  void _onSubmit(Project project) {
    setState(() {
      widget.projects.add(project);
      _currentIndex = 0;
    });

    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text('Projet "${project.name}" ajouté !'), backgroundColor: Colors.green,),
    );
  }

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        leading: Icon(Icons.folder),
        title: Text(widget.title),
        centerTitle: true,
        elevation: 2,
        shadowColor: Colors.black,
      ),
      body: [
        ProjectsList(widget.projects),
        ContributionPage(onProjectSubmitted: _onSubmit)
      ][_currentIndex],
      floatingActionButton: FloatingActionButton(
        onPressed: _addProject,
        child: Icon(Icons.add),
      ),
      bottomNavigationBar: BottomNavigationBar(
        onTap: _onItemTapped,
        backgroundColor: Theme.of(context).colorScheme.primary,
        currentIndex: _currentIndex,
        items: [
          BottomNavigationBarItem(
            icon: Icon(Icons.folder_open),
            label: 'Projet',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.add_circle_outline),
            label: 'Contribuer',
          ),
        ],
        selectedItemColor: Theme.of(context).colorScheme.secondary,
        unselectedItemColor: Colors.blueGrey,
      ),
    );
  }
}

class ProjectsList extends StatelessWidget
{

  final List<Project> projects;

  const ProjectsList(this.projects);

  @override
  Widget build(BuildContext context) {
    return  Column(
      children: [
        Padding(
          padding: const EdgeInsets.all(15.0),
          child: Text('Liste de projets', style: Theme.of(context).textTheme.titleLarge),
        ),
        Expanded(
          child: ListView.builder(
            padding: EdgeInsets.all(10.0),
            itemCount: projects.length,
            itemBuilder: (BuildContext context, int index) {
              return Card(
                color: Theme.of(context).colorScheme.primary,
                elevation: 1,
                child: ListTile(
                  onTap: () => context.push('/details', extra: ScreenArguments(projects[index])),
                  leading: Icon(Icons.folder, color: Theme.of(context).colorScheme.secondary),
                  title: Text(projects[index].name),
                  subtitle: Text(projects[index].desc),
                  trailing: Icon(Icons.chevron_right, color: Theme.of(context).colorScheme.secondary),
                ),
              );
            },
          ),
        ),
      ],
    );
  }

}