
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Multiday Trip Planner</title>
</head>
<body>
	<h2>Plan your multiday trip</h2>
	<s:form action="submitPlan">
		<table>
			<tr>
				<label>Destination</label>

				<input name="planner.City" />
			</tr>
			<tr>
				<label>Number of days</label>
				<input name="planner.Days" />
			</tr>
		</table>
		<table>
			<thead>Choose your preferences
			</thead>
			<tr>
				<td><label>Arts & Entertainment</label> <select
					name="planner.category1Score"><option
							label="Not Interested" value="1" />
						<option label="Would rather do something else" value="2" />
						<option label="Not Sure If Interested" value="3" />
						<option label="Moderately Interested" value="4" />
						<option label="Highly Interested" value="5" /></select></td>
				<td><label>Historical Places</label> <select
					name="planner.category2Score"><option
							label="Not Interested" value="1" />
						<option label="Would rather do something else" value="2" />
						<option label="Not Sure If Interested" value="3" />
						<option label="Moderately Interested" value="4" />
						<option label="Highly Interested" value="5" /></select></td>
			</tr>

			<tr>
				<td><label>Parks & Landscapes</label> <select
					name="planner.category3Score"><option
							label="Not Interested" value="1" />
						<option label="Would rather do something else" value="2" />
						<option label="Not Sure If Interested" value="3" />
						<option label="Moderately Interested" value="4" />
						<option label="Highly Interested" value="5" /></select></td>
				<td><label>Night Life</label> <select
					name="planner.category4Score"><option
							label="Not Interested" value="1" />
						<option label="Would rather do something else" value="2" />
						<option label="Not Sure If Interested" value="3" />
						<option label="Moderately Interested" value="4" />
						<option label="Highly Interested" value="5" /></select></td>
			</tr>
			<tr>
				<td><label>Restaurants</label> <select
					name="planner.category5Score"><option
							label="Not Interested" value="1" />
						<option label="Would rather do something else" value="2" />
						<option label="Not Sure If Interested" value="3" />
						<option label="Moderately Interested" value="4" />
						<option label="Highly Interested" value="5" /></select></td>
				<td><label>Cafes</label> <select name="planner.category6Score" ><option
							label="Not Interested" value="1" />
						<option label="Would rather do something else" value="2" />
						<option label="Not Sure If Interested" value="3" />
						<option label="Moderately Interested" value="4" />
						<option label="Highly Interested" value="5" /></select></td>
			</tr>

			<tr>
				<td>
					<button type="submit">Submit</button>
				</td>
			</tr>
		</table>
	</s:form>

</body>
</html>