package ark

object sandbox {

  println("a");                                   //> a

	println(0 to 10)                          //> Range(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

  object scala {
    val version = "SCALA_VERSION$"
  }

  val xml = <dependencies>
              <dependency>
                <groupId>org.scalanlp</groupId>
                <artifactId>scalala_${ scala.version }</artifactId>
                <version>0.3.1</version>
              </dependency>
              <!-- Apache Commons -->
              <dependency>
                <groupId>commons-io</groupId>
                <artifactId>commons-io</artifactId>
                <version>1.4</version>
              </dependency>
              <dependency>
                <groupId>commons-lang</groupId>
                <artifactId>commons-lang</artifactId>
                <version>2.4</version>
              </dependency>
              <dependency>
                <groupId>joda-time</groupId>
                <artifactId>joda-time</artifactId>
                <version>1.6</version>
              </dependency>
              <dependency>
                <groupId>joda-time</groupId>
                <artifactId>joda-time-hibernate</artifactId>
                <version>1.1</version>
                <exclusions>
                  <exclusion>
                    <groupId>cglib</groupId>
                    <artifactId>cglib-full</artifactId>
                  </exclusion>
                  <exclusion>
                    <groupId>ehcache</groupId>
                    <artifactId>ehcache</artifactId>
                  </exclusion>
                  <exclusion>
                    <groupId>org.hibernate</groupId>
                    <artifactId>hibernate</artifactId>
                  </exclusion>
                  <exclusion>
                    <groupId>antlr</groupId>
                    <artifactId>antlr</artifactId>
                  </exclusion>
                </exclusions>
              </dependency>
              <dependency>
                <groupId>org.scala-lang</groupId>
                <artifactId>scala-library</artifactId>
                <version>${ scala.version }</version>
              </dependency>
              <dependency>
                <groupId>org.scala-lang</groupId>
                <artifactId>scala-compiler</artifactId>
                <version>${ scala.version }</version>
              </dependency>
              <dependency>
                <groupId>com.googlecode.scalaz</groupId>
                <artifactId>scalaz-core_${ scala.version }</artifactId>
                <version>5.0-SNAPSHOT</version>
              </dependency>
              <dependency>
                <groupId>org.scala-tools.testing</groupId>
                <artifactId>specs_${ scala.version }</artifactId>
                <version>1.6.5-SNAPSHOT</version>
              </dependency>
              <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>4.7</version>
                <optional>true</optional>
              </dependency>
              <dependency>
                <groupId>org.scala-tools.testing</groupId>
                <artifactId>test-interface</artifactId>
                <version>0.5</version>
                <optional>true</optional>
              </dependency>
              <dependency>
                <groupId>org.scalatest</groupId>
                <artifactId>scalatest</artifactId>
                <version>1.0.1-for-scala-${ scala.version }-SNAPSHOT</version>
              </dependency>
              <dependency>
                <groupId>org.easymock</groupId>
                <artifactId>easymock</artifactId>
                <version>2.5.1</version>
              </dependency>
              <dependency>
                <groupId>org.easymock</groupId>
                <artifactId>easymockclassextension</artifactId>
                <version>2.4</version>
              </dependency>
              <dependency>
                <groupId>org.scala-tools.testing</groupId>
                <artifactId>scalacheck_${ scala.version }</artifactId>
                <version>1.7</version>
              </dependency>
              <dependency>
                <groupId>org.jmock</groupId>
                <artifactId>jmock</artifactId>
                <version>2.5.1</version>
              </dependency>
              <dependency>
                <groupId>org.jmock</groupId>
                <artifactId>jmock-legacy</artifactId>
                <version>2.5.1</version>
              </dependency>
              <dependency>
                <groupId>org.mockito</groupId>
                <artifactId>mockito-all</artifactId>
                <version>1.8.4</version>
              </dependency>
              <dependency>
                <groupId>cglib</groupId>
                <artifactId>cglib</artifactId>
                <version>2.1_3</version>
              </dependency>
              <dependency>
                <groupId>org.objenesis</groupId>
                <artifactId>objenesis</artifactId>
                <version>1.0</version>
              </dependency>
              <dependency>
                <groupId>net.objectlab.kit.datecalc</groupId>
                <artifactId>datecalc-joda</artifactId>
                <version>1.1.0</version>
              </dependency>
              <dependency>
                <groupId>net.objectlab.kit.datecalc</groupId>
                <artifactId>datecalc-common</artifactId>
                <version>1.1.0</version>
              </dependency>
              <dependency>
                <groupId>org.apache.commons</groupId>
                <artifactId>commons-math</artifactId>
                <version>2.0</version>
              </dependency>
            </dependencies>                       //> xml  : scala.xml.Elem = <dependencies>
                                                  //|               <dependency>
                                                  //|                 <groupId>org.scalanlp</groupId>
                                                  //|                 <artifactId>scalala_$SCALA_VERSION$</artifactId>
                                                  //|                 <version>0.3.1</version>
                                                  //|               </dependency>
                                                  //|               <!-- Apache Commons -->
                                                  //|               <dependency>
                                                  //|                 <groupId>commons-io</groupId>
                                                  //|                 <artifactId>commons-io</artifactId>
                                                  //|                 <version>1.4</version>
                                                  //|               </dependency>
                                                  //|               <dependency>
                                                  //|                 <groupId>commons-lang</groupId>
                                                  //|                 <artifactId>commons-lang</artifactId>
                                                  //|                 <version>2.4</version>
                                                  //|               </dependency>
                                                  //|               <dependency>
                                                  //|                 <groupId>joda-time</groupId>
                                                  //|                 <artifactId>joda-time</artifactId>
                                                  //|                 <version>1.6</version>
                                                  //|               </dependency>
                                                  //|               <depend
                                                  //| Output exceeds cutoff limit.

  val data: Seq[(String, String, String)] = (xml \ "dependency") map { d =>
    val groupId = d \ "groupId" text
    val artifactId = d \ "artifactId" text
    val versionNum = d \ "version" text

    (groupId, artifactId, versionNum)             //> data  : Seq[(String, String, String)] = List((org.scalanlp,scalala_$SCALA_V
                                                  //| ERSION$,0.3.1), (commons-io,commons-io,1.4), (commons-lang,commons-lang,2.4
                                                  //| ), (joda-time,joda-time,1.6), (joda-time,joda-time-hibernate,1.1), (org.sca
                                                  //| la-lang,scala-library,$SCALA_VERSION$), (org.scala-lang,scala-compiler,$SCA
                                                  //| LA_VERSION$), (com.googlecode.scalaz,scalaz-core_$SCALA_VERSION$,5.0-SNAPSH
                                                  //| OT), (org.scala-tools.testing,specs_$SCALA_VERSION$,1.6.5-SNAPSHOT), (junit
                                                  //| ,junit,4.7), (org.scala-tools.testing,test-interface,0.5), (org.scalatest,s
                                                  //| calatest,1.0.1-for-scala-$SCALA_VERSION$-SNAPSHOT), (org.easymock,easymock,
                                                  //| 2.5.1), (org.easymock,easymockclassextension,2.4), (org.scala-tools.testing
                                                  //| ,scalacheck_$SCALA_VERSION$,1.7), (org.jmock,jmock,2.5.1), (org.jmock,jmock
                                                  //| -legacy,2.5.1), (org.mockito,mockito-all,1.8.4), (cglib,cglib,2.1_3), (org.
                                                  //| objenesis,objenesis,1.0), (net.objectlab.kit.datecalc,datecalc-joda,1.1.0),
                                                  //|  (net.objectlab.kit.dat
                                                  //| Output exceeds cutoff limit.
  }

  val CrossBuildArtifact = """([\w-]+)_\$SCALA_VERSION\$""".r
                                                  //> CrossBuildArtifact  : scala.util.matching.Regex = ([\w-]+)_\$SCALA_VERSION\
                                                  //| $

  def dep(a: String, g: String, v: String, cross: Boolean) = {
    val sep = if (cross) "%%" else "%"
    val ident = a.split("-").map(_.capitalize).mkString
    """val %s = "%s" %s "%s" %% "%s" """ format (ident, g, sep, a, v)
  }                                               //> dep: (a: String, g: String, v: String, cross: Boolean)String

  val m = data map {
    case (g, CrossBuildArtifact(a), v) => dep(a, g, v, true)
    case (g, a, v)                     => dep(a, g, v, false)
  } mkString ("\n")                               //> m  : String = "val Scalala = "org.scalanlp" %% "scalala" % "0.3.1" 
                                                  //| val CommonsIo = "commons-io" % "commons-io" % "1.4" 
                                                  //| val CommonsLang = "commons-lang" % "commons-lang" % "2.4" 
                                                  //| val JodaTime = "joda-time" % "joda-time" % "1.6" 
                                                  //| val JodaTimeHibernate = "joda-time" % "joda-time-hibernate" % "1.1" 
                                                  //| val ScalaLibrary = "org.scala-lang" % "scala-library" % "$SCALA_VERSION$" 
                                                  //| val ScalaCompiler = "org.scala-lang" % "scala-compiler" % "$SCALA_VERSION$"
                                                  //|  
                                                  //| val ScalazCore = "com.googlecode.scalaz" %% "scalaz-core" % "5.0-SNAPSHOT" 
                                                  //| 
                                                  //| val Specs = "org.scala-tools.testing" %% "specs" % "1.6.5-SNAPSHOT" 
                                                  //| val Junit = "junit" % "junit" % "4.7" 
                                                  //| val TestInterface = "org.scala-tools.testing" % "test-interface" % "0.5" 
                                                  //| val Scalatest = "org.scalatest" % "scalatest" % "1.0.1-for-scala-$SCALA_VER
                                                  //| SION$-SNAPSHOT" 
                                                  //| val Easymock = "org.easymock" % "easymock" % "2.5.1" 
                                                  //| val Easymockclassextension = "org.easymock" % "easymockcl
                                                  //| Output exceeds cutoff limit.
  
  println(m)                                      //> val Scalala = "org.scalanlp" %% "scalala" % "0.3.1" 
                                                  //| val CommonsIo = "commons-io" % "commons-io" % "1.4" 
                                                  //| val CommonsLang = "commons-lang" % "commons-lang" % "2.4" 
                                                  //| val JodaTime = "joda-time" % "joda-time" % "1.6" 
                                                  //| val JodaTimeHibernate = "joda-time" % "joda-time-hibernate" % "1.1" 
                                                  //| val ScalaLibrary = "org.scala-lang" % "scala-library" % "$SCALA_VERSION$" 
                                                  //| val ScalaCompiler = "org.scala-lang" % "scala-compiler" % "$SCALA_VERSION$"
                                                  //|  
                                                  //| val ScalazCore = "com.googlecode.scalaz" %% "scalaz-core" % "5.0-SNAPSHOT" 
                                                  //| 
                                                  //| val Specs = "org.scala-tools.testing" %% "specs" % "1.6.5-SNAPSHOT" 
                                                  //| val Junit = "junit" % "junit" % "4.7" 
                                                  //| val TestInterface = "org.scala-tools.testing" % "test-interface" % "0.5" 
                                                  //| val Scalatest = "org.scalatest" % "scalatest" % "1.0.1-for-scala-$SCALA_VER
                                                  //| SION$-SNAPSHOT" 
                                                  //| val Easymock = "org.easymock" % "easymock" % "2.5.1" 
                                                  //| val Easymockclassextension = "org.easymock" % "easymockclassextension" %
                                                  //| Output exceeds cutoff limit.

}