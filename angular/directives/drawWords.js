app.directive('drawWords', function () {
        return {
            restrict: 'E',
            scope:{
                lst:'=lst'
            },
            templateUrl: 'views/draw-words.html',
            link: function (scope, element, attrs) {

                var svg = d3.select("svg"),
                    width = +svg.attr("width");

                var format = d3.format(",d");

                var color = d3.scaleOrdinal(d3.schemeCategory20c);

                var pack = d3.pack()
                    .size([width, width])
                    .padding(1.5);

                redrawChart(scope.lst);

                scope.$watchCollection('lst',function (newValue) {
                    redrawChart(newValue);
                });

                function redrawChart(lst){
                    d3.layout.cloud().size([800, 300])
                        .words(lst)
                        .rotate(0)
                        .fontSize(function(d) { return d.size; })
                        .on("end", redrawWords)
                        .start()
                }

                function redrawWords(words){
                    var svg=d3.select("svg");
                    if(!svg){
                        svg = d3.select("body").append("svg");
                    }
                    svg.selectAll("*").remove();

                    svg.attr("width", 850)
                        .attr("height", 350)
                        .attr("class", "wordcloud")
                        .append("g")
                        // without the transform, words words would get cutoff to the left and top, they would
                        // appear outside of the SVG area
                        .attr("transform", "translate(320,200)")
                        .selectAll("text")
                        .data(words)
                        .enter()
                        .append("text")
                        .style("font-size", function(d) { return d.size + "px"; })
                        .style("fill", function(d, i) { return color(i); })
                        .attr("transform", function(d) {
                            return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
                        })
                        .text(function(d) { return d.text; });
                }

            }
        };
    });