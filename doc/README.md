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

    Query	::=	‘from’ SimpleQuery QueryReturnClause ‘;’    SimpleQuery	::=	QueryClause QueryWhereClause?    QueryClause	::=	QueryExpression (, QueryExpression)*    QueryExpression	::=	VarClassDeclaration | VarAssoDeclaration | VarPathDeclaration    VarClassDeclaration	::=	<VariableName> [‘in’ TypeExpression]    VarAssoDeclaration	::=	<VariableName> ‘:’ <VariableName> ‘-’ <VariableName> [‘in’ TypeExpression]    VarPathDeclaration	::=	<VariableName> ‘(’ <VariableName> ‘,’ <VariableName> ‘,’ <PathOperator> [‘,’ <MinLength> [,<MaxLength>]]‘)’    PathOperator	::=	‘next’ | ‘connect’ | ‘until’In turn, each variable is associated to a data model type using a TypeExpression:    TypeExpression	::=	‘(’ TypeExpression ‘)’    TypeExpression	::=	‘not’ TypeExpression    TypeExpression	::=	TypeExpression ‘and’ TypeExpression    TypeExpression	::=	TypeExpression ‘or’ TypeExpression    TypeExpression	::=	<DataModelType>Finally, after describing the query-graph topology using some variables, we add contraints and request what to return in the result:

    QueryWhereClause	::=	‘where’ Expression (‘,’ Expression)*    QueryReturnClause	::=	[‘distinct’] ‘*’ | ([‘distinct’] <VariableName> (‘,’ [‘distinct’] <VariableName>)*)When declaration constraints (the *where* clause), you can use all of these expressions:

    Expression	::=	‘(’ Expression ‘)’    Expression	::=	Expression ‘+’ Expression    Expression	::=	Expression ‘-’ Expression    Expression	::=	Expression ‘*’ Expression    Expression	::=	Expression ‘/’ Expression    Expression	::=	Expression ‘%’ Expression    Expression	::=	‘-’ Expression    Expression	::=	<FunctionName> ‘(’ [FunctionArgs] ‘)’    Expression	::=	Expression ‘>’ Expression    Expression	::=	Expression ‘>=’ Expression    Expression	::=	Expression ‘<’ Expression    Expression	::=	Expression ‘<=’ Expression    Expression	::=	Expression ‘!=’ Expression    Expression	::=	Expression ‘==’ Expression    Expression	::=	Expression ‘::’ RegularExpression    Expression	::=	Expression ‘!:’ RegularExpression    Expression	::=	Expression ‘and’ Expression    Expression	::=	Expression ‘or’ Expression    Expression	::=	‘not’ Expression    Expression	::=	Literal | AttributeAccessor | <VariableName>As stated above, one can define *functions*. As a result of this feature, here is the declaration of a function arguments:

    FunctionArgs	::=	Expression (‘,’ Expression)*Remember that HGE closely associates data to the graph. To access the concrete values, whe define what we call an accessor to attribute, or AttributeAccessor, as follows:    AttributeAccessor	::=	<VariableName> ‘.’ <AttributeName> (‘.’ <AttributeName>)*Additional GQL elements are:    Literal	::=	<Integer> | <Float> | <Character> | <String> | ‘true’ | ‘false’