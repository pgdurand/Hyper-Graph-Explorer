#HGE: querying a graph

##A query: to do what?

As a reminder, HGE was primarily designed to model some real data using a graph topology. When playing with data, it quickly becomes relevant to find a way to locate particular information relying on user-defined constraints (or rules): this is a query.

##A query: how to?
HGE queries can be setup in two main different ways:

* using the API of HGEQuery class
* using a graph query language (GQL)

In addition, it is also possible to add an additional layer to even more simplify the querying of a graph: wrapping HGEQuery API within a more high-level system. As an example, have a look at the BFilter class in project BLAST Filter Tool: this class fully hide the graph stuffs to only expose the BLAST data model.

##HGE: graph query language BNF

Here is the main declaration: a query is a graph relying on the declaration of variables. During query matching, HGE will associate each variable to a graph element (vertex or hyper-edge) for each match.

    Query	::=	‘from’ SimpleQuery QueryReturnClause ‘;’

    QueryWhereClause	::=	‘where’ Expression (‘,’ Expression)*

    Expression	::=	‘(’ Expression ‘)’

    FunctionArgs	::=	Expression (‘,’ Expression)*