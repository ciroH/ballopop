<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select your favorite Candidate</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/Animated-List.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
</head>
<body>
    <section class="voting-main" style="background: url(&quot;${pageContext.request.contextPath}/assets/img/letters-gray.png&quot;);">
        <div class="container">
            <div class="row product-list dev">
                <div class="col-sm-6 col-md-4 product-item animation-element slide-top-left">
                    <div class="product-container bg-transparent">
                        <div class="row">
                            <div class="col-md-12"><img class="rounded-circle img-fluid. max-width:100% mx-auto d-block" src="../assets/img/marcus-aurelius.jpg" width="200" height="200" alt="Marcus Aurelius"></div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <h1 class="candidate-name text-center">Marcus Aurelius</h1>
                            </div>
                        </div>
                        <div class="product-rating"></div>
                        <div class="row">
                            <div class="col-12">
                                <p class="product-description text-center">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ornare sem sed nisl dignissim, facilisis dapibus lacus vulputate. Sed lacinia lacinia magna. </p>
                                <div class="row">
                                    <div class="col">
                                    	<form action="" method="post">
                                    		<input type="hidden" value="">
                                    		<input type="image" class="select-button img-fluid max-width:100% d-block float mx-auto" src="${pageContext.request.contextPath}/assets/img/select-button.jpg">
                                    	</form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/Animated-List.js"></script>
</body>
</html>