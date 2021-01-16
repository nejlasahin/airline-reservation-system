$(document).ready(function() {
    var searchActive = false;
    initSearchForm();
    initSearch();

function initSearch() {
    if($('.search_tab').length)
        {
            $('.search_tab').on('click', function()
            {
                $('.search_tab').removeClass('active');
                $(this).addClass('active');
                var clickedIndex = $('.search_tab').index(this);

                var panels = $('.search_panel');
                panels.removeClass('active');
                $(panels[clickedIndex]).addClass('active');
            });
        }
    }
    function initSearchForm()
    {
        if($('.search_form').length)
        {
            var searchForm = $('.search_form');
            var searchInput = $('.search_content_input');
            var searchButton = $('.content_search');

            searchButton.on('click', function(event)
            {
                event.stopPropagation();

                if(!searchActive)
                {
                    searchForm.addClass('active');
                    searchActive = true;

                    $(document).one('click', function closeForm(e)
                    {
                        if($(e.target).hasClass('search_content_input'))
                        {
                            $(document).one('click', closeForm);
                        }
                        else
                        {
                            searchForm.removeClass('active');
                            searchActive = false;
                        }
                    });
                }
                else
                {
                    searchForm.removeClass('active');
                    searchActive = false;
                }
            });
        }
    }
});

window.addEventListener("DOMContentLoaded", function(e) {
    var links = document.getElementsByTagName("A");
    for(var i=0; i < links.length; i++) {
        if(!links[i].hash) continue;
        if(links[i].origin + links[i].pathname != self.location.href) continue;
        (function(anchorPoint) {
            links[i].addEventListener("click", function(e) {
                anchorPoint.scrollIntoView(true);
                e.preventDefault();
            }, false);
        })(document.getElementById(links[i].hash.replace(/#/, "")));
    }
}, false);