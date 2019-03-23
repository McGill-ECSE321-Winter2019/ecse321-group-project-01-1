<template>
    <div
        id="demo"
        :class="[{'collapsed' : collapsed}]"
    >
        <div class="demo">
            <div class="container">
                <h1>
                    Co-operator
                </h1>
                <p>An ECSE 321 project</p>
                <hr style="margin: 50px 0px;border: 1px solid #e3e3e3;">
                <router-view />
            </div>
            <sidebar-menu
                :menu="menu"
                :collapsed="collapsed"
                :theme="selectedTheme"
                :show-one-child="true"
                @collapse="onCollapse"
                @itemClick="onItemClick"
            />
        </div>
    </div>
</template>

<script>
    const separator = {
        template: `<hr style="border-color: rgba(0, 0, 0, 0.1); margin: 20px;">`
    }
    export default {

        data() {
            return {
                menu: [
                    {
                        header: true,
                        title: 'Getting Started'
                    },
                    {
                        // href: '/',
                        title: 'Installation',
                        icon: 'fa fa-download'
                    },
                    {
                        href: '/hello',
                        title: 'Basic Usage',
                        icon: 'fa fa-code'
                    },
                    {
                        header: true,
                        title: 'Usage'
                    },
                    {
                        // href: '/props',
                        title: 'Props',
                        icon: 'fa fa-cogs'
                    },
                    {
                        // href: '/events',
                        title: 'Events',
                        icon: 'fa fa-bell'
                    },
                    {
                        // href: '/styling',
                        title: 'Styling',
                        icon: 'fa fa-palette'
                    },
                    {
                        header: true,
                        component: separator,
                        visibleOnCollapse: true
                    },
                    {
                        header: true,
                        title: 'Example'
                    },
                    {
                        // href: '/disabled',
                        title: 'Disabled page',
                        icon: 'fa fa-lock',
                        disabled: true
                    },
                    {
                        // href: '#',
                        title: 'Badge',
                        icon: 'fa fa-cog',
                        badge: {
                            text: 'new',
                            class: 'badge-danger'
                        }
                    },
                    {
                        // href: '/page',
                        title: 'Dropdown Page',
                        icon: 'fa fa-list-ul',
                        child: [
                            {
                                href: '/page/sub-page-1',
                                title: 'Sub Page 01',
                                icon: 'fa fa-file-alt'
                            },
                            {
                                href: '/page/sub-page-2',
                                title: 'Sub Page 02',
                                icon: 'fa fa-file-alt'
                            }
                        ]
                    },
                    {
                        // title: 'Multiple Level',
                        icon: 'fa fa-list-alt',
                        child: [
                            {
                                href: '#',
                                title: 'page'
                            },
                            {
                                title: 'Level 2 ',
                                child: [
                                    {
                                        href: '#',
                                        title: 'page'
                                    },
                                    {
                                        href: '#',
                                        title: 'Page'
                                    }
                                ]
                            },
                            {
                                href: '#',
                                title: 'Page'
                            },
                            {
                                title: 'Another Level 2',
                                child: [
                                    {
                                        title: 'Level 3',
                                        child: [
                                            {
                                                href: '#',
                                                title: 'Page'
                                            },
                                            {
                                                href: '#',
                                                title: 'Page'
                                            }
                                        ]
                                    }
                                ]
                            }
                        ]
                    }
                ],
                collapsed: false,
                themes: ['', 'white-theme'],
                selectedTheme: ''
            }
        },
        methods: {
            onCollapse (collapsed) {
                console.log(collapsed)
                this.collapsed = collapsed
            },
            onItemClick (event, item) {
                console.log('onItemClick')
                // console.log(event)
                // console.log(item)
            }
        }
    }
</script>

<style>
    @import url('https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600');

    body,
    html {
        margin: 0;
        padding: 0;
    }

    body {
        font-family: 'Source Sans Pro', sans-serif;
        background-color: #f2f4f7;
    }

    #demo {
        padding-left: 350px;
    }
    #demo.collapsed {
        padding-left: 50px;
    }

    .demo {
        padding: 50px;
    }

    .badge-danger {
        background-color: #ff2a2a;
        color: #fff;
    }

    .container {
        max-width: 600px;
    }

    pre {
        color: #2a2a2e;
        background: #fff;
        border-radius: 2px;
        padding: 10px;
        overflow: auto;
    }
</style>
