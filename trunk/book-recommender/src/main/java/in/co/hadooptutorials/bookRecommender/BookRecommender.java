package in.co.hadooptutorials.bookRecommender;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class BookRecommender {

	public static void main(String args[]) {

		try {
			DataModel dataModel = new FileDataModel(new File(
					"src/main/resources/dataset.csv"));
			UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(
					dataModel);
			UserNeighborhood userNeighborhood = new ThresholdUserNeighborhood(
					0.1, userSimilarity, dataModel);
			UserBasedRecommender recommender = new GenericUserBasedRecommender(
					dataModel, userNeighborhood, userSimilarity);
			List<RecommendedItem> recommendations = recommender.recommend(2, 3);
			for (RecommendedItem item : recommendations) {
				System.out.println(item);
			}
		} catch (IOException e) {

			e.printStackTrace();
		} catch (TasteException e) {

			e.printStackTrace();
		}
	}
}
